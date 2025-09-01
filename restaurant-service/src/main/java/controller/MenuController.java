package com.example.restaurant.controller;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.store.InMemoryStore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MenuController {
    private final InMemoryStore store;

    public MenuController(InMemoryStore store) {
        this.store = store;
    }

    @PostMapping("/restaurants/{id}/menu-items")
    public MenuItem addItem(@PathVariable String id, @RequestBody MenuItem m) {
        if (!store.restaurants.containsKey(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        m.setId(UUID.randomUUID().toString());
        m.setRestaurantId(id);
        if (m.getName() == null || m.getName().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        store.menuItems.put(m.getId(), m);
        return m;
    }

    @GetMapping("/restaurants/{id}/menu")
    public List<MenuItem> listMenu(@PathVariable String id) {
        if (!store.restaurants.containsKey(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        return store.menuItems.values().stream()
                .filter(mi -> id.equals(mi.getRestaurantId()))
                .collect(Collectors.toList());
    }

    @PutMapping("/menu-items/{id}")
    public MenuItem updateItem(@PathVariable String id, @RequestBody MenuItem update) {
        var existing = store.menuItems.get(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found");
        if (update.getName() != null) existing.setName(update.getName());
        if (update.getPriceCents() != 0) existing.setPriceCents(update.getPriceCents());
        existing.setAvailable(update.isAvailable());
        return existing;
    }

    @DeleteMapping("/menu-items/{id}")
    public void deleteItem(@PathVariable String id) {
        store.menuItems.remove(id);
    }

    // Bulk lookup used by Order Service
    @GetMapping("/menu-items")
    public List<MenuItem> bulk(@RequestParam String ids) {
        List<MenuItem> result = new ArrayList<>();
        for (String s : ids.split(",")) {
            MenuItem mi = store.menuItems.get(s.trim());
            if (mi != null) result.add(mi);
        }
        return result;
    }
}

