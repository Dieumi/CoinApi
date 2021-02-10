package com.levio.wallet.api.controller;

import com.levio.wallet.api.model.AuthenticationRequest;
import com.levio.wallet.api.model.AuthenticationResponse;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    @PostMapping("/deployCagnote")
    @ResponseBody
    public ResponseEntity<?> createCagnotte() throws Exception {

        contractService.deployContractCagnotte();

        return ResponseEntity.ok("test");
    }

}
