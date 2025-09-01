package com.example.restaurant.controller;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.store.InMemoryStore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin
public class RestaurantController {
    private final InMemoryStore store;

    public RestaurantController(InMemoryStore store) {
        this.store = store;
    }

    @GetMapping
    public List<Restaurant> list() {
        return new ArrayList<>(store.restaurants.values());
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant r) {
        r.setId(UUID.randomUUID().toString());
        if (r.getCuisines() == null) r.setCuisines(new java.util.ArrayList<>());
        store.restaurants.put(r.getId(), r);
        return r;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable String id) {
        var r = store.restaurants.get(id);
        if (r == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        return r;
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable String id, @RequestBody Restaurant update) {
        var existing = store.restaurants.get(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        if (update.getName() != null) existing.setName(update.getName());
        if (update.getCuisines() != null) existing.setCuisines(update.getCuisines());
        return existing;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        store.restaurants.remove(id);
        // remove menu items of this restaurant too
        store.menuItems.values().removeIf(mi -> id.equals(mi.getRestaurantId()));
    }
}
