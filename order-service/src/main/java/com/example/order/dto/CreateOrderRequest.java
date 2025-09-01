package main.java.com.example.order.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {
    public static class Item {
        private String menuItemId;
        private int qty;

        public Item() {}
        public Item(String menuItemId, int qty) {
            this.menuItemId = menuItemId;
            this.qty = qty;
        }
        public String getMenuItemId() { return menuItemId; }
        public void setMenuItemId(String menuItemId) { this.menuItemId = menuItemId; }
        public int getQty() { return qty; }
        public void setQty(int qty) { this.qty = qty; }
    }

    private List<Item> items = new ArrayList<>();
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}

