package com.levio.wallet.api.repository;

import com.levio.wallet.api.model.WalletLevio;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletLevio,Integer> {

    public Optional<WalletLevio> findByUserId(long id) throws NotFoundException;
    public Optional<WalletLevio> findByPublicKey(String publicKey) throws NotFoundException;
    public Optional<WalletLevio> findByAddress(String address) throws NotFoundException;
}
