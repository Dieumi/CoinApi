package com.levio.wallet.api;

import com.levio.wallet.api.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@ConfigurationPropertiesScan
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public Web3j web3(){
        Web3j web3j= Web3j.build(new HttpService("https://rinkeby.infura.io/v3/undefined"));
        return web3j;
    }
}
