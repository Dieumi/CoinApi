package com.levio.wallet.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    public String levioCoin;
    public Long receiver;
}
