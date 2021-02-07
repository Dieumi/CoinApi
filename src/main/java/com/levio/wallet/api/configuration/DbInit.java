package com.levio.wallet.api.configuration;

import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.UserRepository;
import com.levio.wallet.api.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Component
public class DbInit {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    PasswordEncoder bcrypt;

    @Autowired
    Web3j web3j;
    @PostConstruct
    private void postConstruct() throws ExecutionException, InterruptedException {
        Users admin = Users.builder()
                .username("admin")
                .password(bcrypt.encode("admin"))
                .email("pierre.lochouarn@levio.ca")
                .role("ADMIN").build();
        EthGetBalance ethGetBalance = web3j.ethGetBalance("0xa1F3Ae38350Ffa9e07506b3c475e17599ABcc095", DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger wei = ethGetBalance.getBalance();
        BigDecimal solde = Convert.fromWei(String.valueOf(wei), Convert.Unit.ETHER);
        WalletLevio adminWallet = WalletLevio.builder()
                .address("0xa1F3Ae38350Ffa9e07506b3c475e17599ABcc095")
                .privateKey("0x33ebdeff9270bb19a7a65bc00cca132636f8e550d4242714cabe417c720d179c")
                .user(admin)
                .password(bcrypt.encode("test1234"))
                .solde(solde.floatValue())
                .build();
        if(!userRepository.findByUsername("admin").isPresent()){
            userRepository.save(admin);
            walletRepository.save(adminWallet);
        }




    }
}
