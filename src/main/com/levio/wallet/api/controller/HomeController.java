package com.levio.wallet.api.controller;

import com.levio.wallet.api.model.AuthenticationRequest;
import com.levio.wallet.api.model.AuthenticationResponse;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.service.UserDetailsServiceImpl;
import com.levio.wallet.api.service.WalletService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.levio.wallet.api.util.JwtUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    WalletService walletService;
    @GetMapping("/")
    @ResponseBody
    public String home()  {
        return ("Welcome ");
    }

    @GetMapping("/listUser")
    @ResponseBody
    public List<Users> getAllUser()  {
        List<Users> list =userDetailsService.getListofName();

        return list;

    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        Users userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        userDetails.setPassword("");
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(userDetails,jwt));
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody Users user) throws Exception {
        try {
         Users userCreated=userDetailsService.registerUser(user);
         return ResponseEntity.ok(userCreated);
        }catch(Exception e){
            throw new Exception("User not created",e);
        }

    }
}
