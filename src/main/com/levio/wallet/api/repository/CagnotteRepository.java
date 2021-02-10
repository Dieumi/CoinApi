package com.levio.wallet.api.repository;

import com.levio.wallet.api.model.Cagnotte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CagnotteRepository extends JpaRepository<Cagnotte,Integer> {
    public Cagnotte findByAddress(String address);
    public List<Cagnotte> findAll();
    public Optional<Cagnotte> findById(long id);
}
