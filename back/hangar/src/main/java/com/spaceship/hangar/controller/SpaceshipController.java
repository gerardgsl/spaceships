package com.spaceship.hangar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spaceship.hangar.model.Spaceship;
import com.spaceship.hangar.service.SpaceshipService;

import java.util.List;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    @Autowired
    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @GetMapping
    public List<Spaceship> getAllSpaceships(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return spaceshipService.getAllSpaceships(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        return spaceshipService.getSpaceshipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Spaceship> getSpaceshipsByName(@RequestParam String name) {
        return spaceshipService.getSpaceshipsByName(name);
    }

    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return spaceshipService.saveSpaceship(spaceship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        if (spaceshipService.getSpaceshipById(id).isPresent()) {
            spaceship.setId(id);
            return ResponseEntity.ok(spaceshipService.saveSpaceship(spaceship));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
        return ResponseEntity.noContent().build();
    }
}
