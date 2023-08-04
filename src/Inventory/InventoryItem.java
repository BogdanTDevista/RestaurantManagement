package Inventory;

import menu.MenuItem;

public class InventoryItem {
    private String name;

    private long menuItemID;

    private int quantity;
    private int thresholdLevel;
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(Long menuItemID) {
        this.menuItemID = menuItemID;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getThresholdLevel(){
        return thresholdLevel;
    }

    public void setThresholdLevel(int thresholdLevel){
        this.thresholdLevel = thresholdLevel;
    }
}
