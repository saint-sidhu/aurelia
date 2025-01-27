package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserCooldownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCooldownRepo extends JpaRepository<UserCooldownEntity, Integer> {

    @Query(value = "SELECT * FROM overlord_aurelia.user_cooldown WHERE user_id = ?1", nativeQuery = true)
    UserCooldownEntity findByUserId(int userId);
}
