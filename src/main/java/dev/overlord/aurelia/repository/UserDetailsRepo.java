package dev.overlord.aurelia.repository;

import dev.overlord.aurelia.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity,Integer> {
}
