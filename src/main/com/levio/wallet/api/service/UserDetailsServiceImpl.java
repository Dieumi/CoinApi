package com.levio.wallet.api.service;

import com.levio.wallet.api.model.Users;
import com.levio.wallet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder bcrypt;

    @Override
    public Users loadUserByUsername(String name) throws UsernameNotFoundException {


            Optional<Users> user= this.userRepository.findByUsername(name);
            List<Users> listUser = this.userRepository.findAll();

            user.orElseThrow(()->new UsernameNotFoundException("not found "+ name));
            return user.get();
    }

    public Users registerUser(Users user) throws Exception {
        user.setPassword(bcrypt.encode(user.getPassword()));

        return  userRepository.save(user);
    }
    public List<Users> getListofName(){
        return userRepository.findAll();
    }
}
