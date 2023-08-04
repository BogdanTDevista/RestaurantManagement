package Inventory;

public class InventoryDisplay {



    public InventoryDisplay(){

    }
    public void displayInventoryItems(InventoryService inventoryService){

        int index = 0;
        for(InventoryItem inventoryItem: inventoryService.getInventoryMap().values()){
            System.out.println("Item " +  ++index);
            System.out.println("Name: " + inventoryItem.getName());
            System.out.println("Threshold level: " + inventoryItem.getThresholdLevel());
            System.out.println("Quantity: " + inventoryItem.getQuantity());
            System.out.println();
        }
    }

}
