package com.levio.wallet.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;
    private String role;



    @Override
    public Collection<Role> getAuthorities() {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(getRole()));
        return  listRole;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



