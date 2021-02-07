package com.levio.wallet.api.controller;


import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.TransactionDTO;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.service.TransactionService;
import com.levio.wallet.api.service.WalletService;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionService transactionService;



    @GetMapping("/wallet")
    @ResponseBody
    public WalletLevio getWallet() throws NotFoundException, ExecutionException, InterruptedException {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WalletLevio wallet=walletService.getWalletByUserId(principal.getId());
        if(wallet != null){
            return (wallet);
        }
        return (null);
    }
    @GetMapping("/wallet/transaction/{id}")
    @ResponseBody
    public List<Transaction> getTransactionByWalletId(@PathVariable String id) throws Exception {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getId().equals(Long.parseLong(id))){
            WalletLevio wallet=walletService.getWalletByUserId(Long.parseLong(id));

            if(wallet != null ) {
                List<Transaction> transactions = transactionService.getTransactionByWalletId(wallet);
                return (transactions);
            }
        }else{
            throw new Exception("Unauthorized");
        }

        return Collections.emptyList();

    }
    @PostMapping("/createWallet")
    @ResponseBody
    public WalletLevio createWallet( ) throws Exception {
        System.out.println("Create Wallet");
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal " + principal.toString());
        var wallet = walletService.createWallet();
        if(walletService.validateCreation()){

            return wallet;
        }
      return null;
    }
    @PostMapping("/sendTransaction")
    @ResponseBody
    public boolean sendTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        System.out.println("Send transaction for "+transaction+" Wallet");
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        WalletLevio from = walletService.getWalletByUserId(principal.getId());
        System.out.println("TO"+transaction.getReceiver());
        WalletLevio to = walletService.getWalletByUserId((Long.parseLong(transaction.getReceiver())));
        System.out.println("Send transaction from "+from);
        if(walletService.validateSolde(from.getAddress(),new BigDecimal(transaction.getLevioCoin()))){
            transactionService.sendTransaction(from,to,new BigDecimal(transaction.getLevioCoin()));
        }

        return true;
    }
}
