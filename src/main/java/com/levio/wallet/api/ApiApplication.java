package com.levio.wallet.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.levio.wallet.api.repository"})
@ConfigurationPropertiesScan
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public Web3j web3(){

        Web3j web3j= Web3j.build(new HttpService("http://localhost:8543"));
        return web3j;
    }

    @Bean
    public StaticGasProvider staticGasProvider(){
        StaticGasProvider gasProvider =  new StaticGasProvider(BigInteger.valueOf(40000),BigInteger.valueOf(8000000L));
        return gasProvider;
    }
}
