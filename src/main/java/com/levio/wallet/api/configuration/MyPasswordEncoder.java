package com.levio.wallet.api.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        if(charSequence.toString().equals(s)){
            return true;
        }
        return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
