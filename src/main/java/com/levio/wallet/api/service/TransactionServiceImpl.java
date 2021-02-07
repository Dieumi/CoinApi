package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.TransactionRepository;
import com.levio.wallet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    Web3j web3j;

    @Autowired
    TransactionRepository  transactionRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public TransactionReceipt sendTransaction(WalletLevio from, WalletLevio to, BigDecimal levioCoin) throws Exception {


        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Credentials credentials = Credentials.create(from.getPrivateKey());
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j,credentials,to.getAddress(), levioCoin, Convert.Unit.ETHER).send();
        System.out.println(transactionReceipt.toString());
        Transaction transaction = Transaction.builder().date(new Date())
                .transactionHash(transactionReceipt.getTransactionHash())
                .levioCoin(levioCoin.toString())
                .sender(from)
                .receiver(to)
                .build();
        transactionRepository.save(transaction);


        return transactionReceipt;
    }

    @Override
    public List<Transaction> getTransactionByWalletId(WalletLevio id) {
        Optional<List<Transaction>> transactionsList=transactionRepository.findAllByReceiverOrSenderOrderByDate(id,id);
        return transactionsList.get();
    }
}
