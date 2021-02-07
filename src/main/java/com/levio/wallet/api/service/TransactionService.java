package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.WalletLevio;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    public TransactionReceipt sendTransaction(WalletLevio from, WalletLevio to, BigDecimal levioCoin) throws Exception;

    public List<Transaction> getTransactionByWalletId(WalletLevio id);
}
