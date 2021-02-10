package com.levio.wallet.api.repository;


import com.levio.wallet.api.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);

    @Override
    Page<Users> findAll(Pageable pageable);
}
