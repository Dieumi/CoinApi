package com.levio.wallet.api.repository;

import com.levio.wallet.api.model.Transaction;
import com.levio.wallet.api.model.WalletLevio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    public Optional<List<Transaction>> findAllByReceiverOrSenderOrderByDate(WalletLevio receiver, WalletLevio sender);


}
