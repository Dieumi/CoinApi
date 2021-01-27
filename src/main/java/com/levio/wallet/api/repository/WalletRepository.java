package com.levio.wallet.api.repository;

import com.levio.wallet.api.model.WalletLevio;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletLevio,Integer> {

    public WalletLevio findByUserId(long id) throws NotFoundException;
    public WalletLevio findByPublicKey(String publicKey) throws NotFoundException;
}
