package com.example.restaurant.store;

import com.example.restaurant.model.MenuItem;
import com.example.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStore {
    public final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();
    public final Map<String, MenuItem> menuItems = new ConcurrentHashMap<>();
}

