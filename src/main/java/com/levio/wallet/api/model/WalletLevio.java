package com.levio.wallet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletLevio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String publicKey;


    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String privateKey;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String credential;

    private String address;

    private String walletFile;

    private Float solde;

    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Transaction> transactionSent;

    @OneToMany(mappedBy = "receiver",fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Transaction> transactionReceived;
    @OneToOne
    @JsonIgnoreProperties("password")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;
}
