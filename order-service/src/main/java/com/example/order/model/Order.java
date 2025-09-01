package main.java.com.example.order.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private List<OrderItem> items = new ArrayList<>();
    private int subtotalCents;
    private int taxCents;
    private int totalCents;
    private String status; // CREATED

    public Order() {}

    public Order(String id, List<OrderItem> items, int subtotalCents, int taxCents, int totalCents, String status) {
        this.id = id;
        this.items = items;
        this.subtotalCents = subtotalCents;
        this.taxCents = taxCents;
        this.totalCents = totalCents;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public int getSubtotalCents() { return subtotalCents; }
    public void setSubtotalCents(int subtotalCents) { this.subtotalCents = subtotalCents; }

    public int getTaxCents() { return taxCents; }
    public void setTaxCents(int taxCents) { this.taxCents = taxCents; }

    public int getTotalCents() { return totalCents; }
    public void setTotalCents(int totalCents) { this.totalCents = totalCents; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

