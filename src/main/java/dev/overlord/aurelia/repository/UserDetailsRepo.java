package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity, Integer> {

    @Query(value = "SELECT * FROM overlord_aurelia.user_details WHERE user_name = ?1", nativeQuery = true)
    UserDetailsEntity findByUserName(String userName);
}
