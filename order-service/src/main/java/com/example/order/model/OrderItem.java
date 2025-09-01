package main.java.com.example.order.model;

public class OrderItem {
    private String menuItemId;
    private int qty;
    private int priceCentsSnapshot;
    private String nameSnapshot;

    public OrderItem() {}

    public OrderItem(String menuItemId, int qty, int priceCentsSnapshot, String nameSnapshot) {
        this.menuItemId = menuItemId;
        this.qty = qty;
        this.priceCentsSnapshot = priceCentsSnapshot;
        this.nameSnapshot = nameSnapshot;
    }

    public String getMenuItemId() { return menuItemId; }
    public void setMenuItemId(String menuItemId) { this.menuItemId = menuItemId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public int getPriceCentsSnapshot() { return priceCentsSnapshot; }
    public void setPriceCentsSnapshot(int priceCentsSnapshot) { this.priceCentsSnapshot = priceCentsSnapshot; }

    public String getNameSnapshot() { return nameSnapshot; }
    public void setNameSnapshot(String nameSnapshot) { this.nameSnapshot = nameSnapshot; }
}
