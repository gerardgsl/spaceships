package com.spaceship.hangar.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spaceship.hangar.model.Spaceship;
import com.spaceship.hangar.repository.SpaceshipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    @Autowired
    public SpaceshipService(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    @Cacheable("spaceships")
    public List<Spaceship> getAllSpaceships(int page, int size) {
        return spaceshipRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id);
    }

    public List<Spaceship> getSpaceshipsByName(String name) {
        return spaceshipRepository.findByNameContaining(name);
    }

    public Spaceship saveSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }
}

