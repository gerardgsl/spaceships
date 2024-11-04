package com.spaceship.hangar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spaceship.hangar.model.Spaceship;
import java.util.List;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    List<Spaceship> findByNameContaining(String name);
}