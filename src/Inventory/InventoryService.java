package Inventory;

import Order.Order;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private Map<Integer, InventoryItem> mapInventory = new HashMap<>();

    private static int id = 0;

    public InventoryService(){

    }

    public Map<Integer, InventoryItem> getInventoryMap(){
        return mapInventory;
    }

//    public InventoryItem updateInventoryItem(InventoryItem inventoryItem){
//
//    }

    public InventoryItem retrieveInventoryDetails(int id){
        return mapInventory.get(id);
    }

}
