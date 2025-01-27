package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserProgressionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgressionRepo extends JpaRepository<UserProgressionEntity,Integer> {

    @Query(value = "SELECT * FROM overlord_aurelia.user_progression WHERE user_id = ?1", nativeQuery = true)
    UserProgressionEntity getUserProgressionById(int id);
}
