package Inventory;

public class InventoryItem {
    private String name;
    private int quantity;
    private int thresholdLevel;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
