package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserDataEntity,Integer> {

    @Query(value = "SELECT balance FROM overlord_aurelia.user_data WHERE user_name = ?1", nativeQuery = true)
    int findBalanceByUserName(String userName);

    @Query(value = "SELECT * FROM overlord_aurelia.user_data WHERE user_name = ?1", nativeQuery = true)
    UserDataEntity findByUserName(String user);
}
