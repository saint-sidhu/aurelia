package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.CurrencyTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyTierRepo extends JpaRepository<CurrencyTierEntity,Integer> {
}
