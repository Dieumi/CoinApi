package com.levio.wallet.api.controller;

import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.service.WalletService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    WalletService walletService;
    @GetMapping("/")
    @ResponseBody
    public String home() throws NotFoundException {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WalletLevio wallet=walletService.getWalletByUserId(principal.getId());
        if(wallet != null){
            return ("<h1>Welcome " +principal.getUsername()+"</h1><br/><h1>your wallet :  " +wallet.getPublicKey()+"</h1>");
        }
        return ("<h1>Welcome " +principal.getUsername()+"</h1>");
    }
}
