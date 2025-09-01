package com.example.restaurant.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private List<String> cuisines = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String id, String name, List<String> cuisines) {
        this.id = id;
        this.name = name;
        this.cuisines = cuisines != null ? cuisines : new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getCuisines() { return cuisines; }
    public void setCuisines(List<String> cuisines) { this.cuisines = cuisines; }
}
