package com.levio.wallet.api.service;

import com.levio.wallet.api.model.WalletLevio;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;


public interface WalletService {

    public WalletLevio createWallet() throws Exception;
    public boolean validateCreation() throws NotFoundException;
    public WalletLevio getWalletByUserId(Long userId) throws NotFoundException, ExecutionException, InterruptedException;


    public boolean validateSolde(String address, BigDecimal levioCoin) throws Exception;

}
