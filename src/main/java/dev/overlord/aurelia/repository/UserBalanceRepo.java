package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepo extends JpaRepository<UserBalanceEntity,Integer> {

    @Query(value = "SELECT * FROM overlord_aurelia.user_balance WHERE user_name = ?1", nativeQuery = true)
    UserBalanceEntity findByUserName(String user);
}
