package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.WalletRepository;
import javassist.NotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import org.web3j.utils.Convert;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@Primary
public class WalletServiceImpl implements WalletService {

    @Autowired
    public WalletRepository walletRepository;


    @Autowired
    public Web3j web3j;
    @Autowired
    PasswordEncoder bcrypt;
    public WalletLevio createWallet() throws Exception {
        try {
            Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String pass = RandomStringUtils.random(12, 65, 90, true, true);
            ECKeyPair keyPair = Keys.createEcKeyPair();
            WalletFile wallet = Wallet.createStandard(pass, keyPair);

            WalletLevio walletLevio = WalletLevio.builder()
                    .user(principal)
                    .privateKey(keyPair.getPrivateKey().toString(16))
                    .publicKey(keyPair.getPublicKey().toString(16))
                    .password(bcrypt.encode(pass))
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
        Optional<WalletLevio> walletLevio = walletRepository.findByUserId(principal.getId());
        return walletLevio.isPresent();
    }

    @Override
    public WalletLevio getWalletByUserId(Long userId) throws NotFoundException, ExecutionException, InterruptedException {
        Optional<WalletLevio> wallet=walletRepository.findByUserId(userId);
        wallet.orElseThrow(()->new NotFoundException("Not found"));
        EthGetBalance ethGetBalance = web3j.ethGetBalance(wallet.get().getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger wei = ethGetBalance.getBalance();
        BigDecimal solde = Convert.fromWei(String.valueOf(wei), Convert.Unit.ETHER);
        wallet.get().setPassword("");
        wallet.get().setSolde(solde.floatValue());
        System.out.println(solde.toString());
        System.out.println(wallet.get().toString());
        return wallet.get();
    }



    @Override
    public boolean validateSolde(String address, BigDecimal levioCoin) throws Exception {

        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
        System.out.println(ethGetBalance.getBalance());
        BigInteger wei = ethGetBalance.getBalance();
        BigDecimal solde = Convert.fromWei(String.valueOf(wei), Convert.Unit.ETHER);
        if(solde.compareTo(levioCoin) >= 0 ){
            return true;
        }
        return false;
    }


}
