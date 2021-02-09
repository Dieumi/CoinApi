package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Cagnotte;
import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.model.WalletLevio;
import com.levio.wallet.api.repository.CagnotteRepository;
import com.levio.wallet.api.smartContract.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Date;

@Service
public class ContractService {

    @Autowired
    Web3j web3j;

    @Autowired
    WalletService walletService;
    @Autowired
    StaticGasProvider staticGasProvider;

    @Autowired
    CagnotteRepository cagnotteRepository;

    public Cagnotte deployContractCagnotte() throws Exception {
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WalletLevio wallet = walletService.getWalletByUserId(principal.getId());
        Credentials credentials = Credentials.create(wallet.getPrivateKey());
        Lottery lottery=Lottery.deploy(web3j,credentials,staticGasProvider, BigInteger.valueOf(5)).sendAsync().get();

        System.out.println(lottery.getContractAddress());
        Cagnotte cagnotte = Cagnotte.builder()
                .address(lottery.getContractAddress())
                .dateDebut(new Date())
                .dateFin(new Date())
                .open(true)
                .build();
        cagnotteRepository.save(cagnotte);
        return cagnotte;
    }
}
