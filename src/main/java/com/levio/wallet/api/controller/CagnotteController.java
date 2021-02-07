package com.levio.wallet.api.controller;


import com.levio.wallet.api.model.BuyTicket;
import com.levio.wallet.api.model.Cagnotte;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.service.CagnotteService;
import com.levio.wallet.api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CagnotteController {

    @Autowired
    CagnotteService cagnotteService;
    @Autowired
    UserDetailsService userDetailsService;
    @PostMapping("/buyTicket")
    @ResponseBody
    public ResponseEntity<?> createCagnotte(@RequestBody BuyTicket buyTicket) throws Exception {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cagnotte cagnotte = cagnotteService.getById(buyTicket.getCagnotte());
        cagnotteService.buyTicket(principal,cagnotte,Float.parseFloat(buyTicket.getLevioCoin()));

        return ResponseEntity.ok("test");
    }
}
