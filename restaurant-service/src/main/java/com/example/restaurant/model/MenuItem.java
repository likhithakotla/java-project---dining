package com.example.restaurant.model;

public class MenuItem {
    private String id;
    private String restaurantId;
    private String name;
    private int priceCents;
    private boolean available = true;

    public MenuItem() {}

    public MenuItem(String id, String restaurantId, String name, int priceCents, boolean available) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.priceCents = priceCents;
        this.available = available;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPriceCents() { return priceCents; }
    public void setPriceCents(int priceCents) { this.priceCents = priceCents; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

