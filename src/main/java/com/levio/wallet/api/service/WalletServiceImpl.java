package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.WalletRepository;
import javassist.NotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

@Service
@Primary
public class WalletServiceImpl implements WalletService {

    @Autowired
    public WalletRepository walletRepository;

    @Autowired
    public Web3j web3j;

    public WalletLevio createWallet() throws Exception {
        try {
            Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String pass = RandomStringUtils.random(2, 65, 90, true, true);
            ECKeyPair keyPair = Keys.createEcKeyPair();
            WalletFile wallet = Wallet.createStandard(pass, keyPair);

            Credentials credentials = Credentials.create(keyPair.getPrivateKey().toString(16),keyPair.getPublicKey().toString(16));

            WalletLevio walletLevio = WalletLevio.builder()
                    .user(principal)
                    .privateKey(keyPair.getPrivateKey().toString(16))
                    .publicKey(keyPair.getPublicKey().toString(16))
                    .password(pass)
                    .address("0x"+wallet.getAddress())
                    .build();
            walletRepository.save(walletLevio);
            return walletLevio;
        } catch (Exception e) {
           throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean validateCreation() throws NotFoundException {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WalletLevio walletLevio = walletRepository.findByUserId(principal.getId());
        if( walletLevio == null){
            return true;
        }
        return false;
    }

    @Override
    public WalletLevio getWalletByUserId(Long userId) throws NotFoundException {
        WalletLevio wallet=walletRepository.findByUserId(userId);
        return wallet;
    }

    @Override
    public String sendTransaction(String from, String to, BigDecimal levioCoin) throws Exception {
        System.out.println("WalletService");
        if(this.validateSolde(from,levioCoin)){
            System.out.println("WalletService");
            WalletLevio walletLevio = walletRepository.findByPublicKey(from);

            Credentials credentials = Credentials.create(walletLevio.getPrivateKey());
            TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j,credentials,to, levioCoin, Convert.Unit.ETHER).send();
            System.out.println(transactionReceipt.toString());

        }
        return "Transaction Sent";
    }

    @Override
    public boolean validateSolde(String address, BigDecimal levioCoin) throws Exception {
        EthGetBalance ethGetBalance = this.web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        System.out.println(ethGetBalance.getBalance());
        return false;
    }


}
