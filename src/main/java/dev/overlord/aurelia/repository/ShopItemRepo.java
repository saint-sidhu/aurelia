package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.ShopItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepo extends JpaRepository<ShopItemEntity,Integer> {

}
