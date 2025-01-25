package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.CurrencyDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDetailsRepo extends JpaRepository<CurrencyDetailsEntity,Integer> {

    CurrencyDetailsEntity findByCurrencyTier(String currencyTier);
}
