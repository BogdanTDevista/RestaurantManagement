package Inventory;

import java.util.Map;

public class LowStockAlert implements Alert{
    @Override
    public void alert(Map<Integer, InventoryItem> inventoryMap, int id) {
        System.out.println("The stock is low for: " + inventoryMap.get(id).getName());
    }
}
