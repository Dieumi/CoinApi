package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Cagnotte;
import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.CagnotteRepository;
import com.levio.wallet.api.repository.TransactionRepository;
import com.levio.wallet.api.smartContract.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CagnotteServiceImpl implements CagnotteService{
    @Autowired
    Web3j web3j;
    @Autowired
    WalletService walletService;

    @Autowired
    StaticGasProvider staticGasProvider;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CagnotteRepository cagnotteRepository;
    @Override
    public boolean buyTicket(Users buyer, Cagnotte cagnotte,Float amount) throws Exception {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WalletLevio wallet = walletService.getWalletByUserId(principal.getId());
        Credentials credentials = Credentials.create(wallet.getPrivateKey());
        Lottery lottery = Lottery.load(cagnotte.address,web3j,credentials,staticGasProvider);
        System.out.println("Achat de ticket"+Convert.toWei(amount.toString(), Convert.Unit.ETHER) + " int value" + Convert.toWei(amount.toString(), Convert.Unit.ETHER).toBigInteger());
        TransactionReceipt transaction = lottery.accept(Convert.toWei(amount.toString(), Convert.Unit.ETHER).toBigInteger()).send();

        System.out.println(transaction.toString());
        Transaction transactionToSave = Transaction.builder().date(new Date())
                .transactionHash(transaction.getTransactionHash())
                .levioCoin(amount.toString())
                .sender(wallet)
                .reason("Ticket cagnotte")
                .build();
        transactionRepository.save(transactionToSave);

        return true;
    }

    @Override
    public List<Cagnotte> getAll() {
        return cagnotteRepository.findAll();
    }

    @Override
    public Cagnotte getById(String id) {
        Optional<Cagnotte> cagnotte =cagnotteRepository.findById(Long.parseLong(id));
        return cagnotte.get();
    }

    @Override
    public Cagnotte getByAddress(String address) {
        return cagnotteRepository.findByAddress(address);
    }
}
