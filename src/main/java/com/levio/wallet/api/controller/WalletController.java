package com.levio.wallet.api.controller;


import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.service.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class WalletController {

    @Autowired
    WalletServiceImpl walletService;

    @PostMapping("/createWallet")
    @ResponseBody
    public String createWallet( ) throws Exception {
        System.out.println("Create Wallet");
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal " + principal.toString());
        if(walletService.validateCreation()){
            var wallet = walletService.createWallet();
            return "account created";
        };
      return "you already have an account";
    }
    @PostMapping("/sendTransaction")
    @ResponseBody
    public String sendTransaction(@RequestBody Transaction transaction) throws Exception {
        System.out.println("Send transaction for "+transaction+" Wallet");
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        WalletLevio from = walletService.getWalletByUserId(principal.getId());
        WalletLevio to = walletService.getWalletByUserId(transaction.getReceiver());
        System.out.println("Send transaction from "+from);
        walletService.sendTransaction(from.getPublicKey(),to.getPublicKey(),new BigDecimal(transaction.getLevioCoin()));
        return "Transaction sent";
    }
}
