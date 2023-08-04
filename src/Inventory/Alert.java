package Inventory;

import Inventory.InventoryItem;

import java.util.Map;

public interface Alert {
    public void alert(Map<Integer, InventoryItem> invetoryMap, int id);
}
