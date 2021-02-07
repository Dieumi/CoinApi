package com.levio.wallet.api.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements GrantedAuthority {
    public String role;
    @Override
    public String getAuthority() {
        return getRole();
    }
}
