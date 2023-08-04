package Inventory;

import Order.Order;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private Map<Integer, InventoryItem> mapInventory = new HashMap<>();

    private List<Alert> alerts = new ArrayList<>();

    private static int id = 0;

    public InventoryService(){

    }

    public Map<Integer, InventoryItem> getInventoryMap(){
        return mapInventory;
    }

    public void addObserver(Alert alert) {
        this.alerts.add(alert);
    }

    public void removeObserver(Alert alert) {
        this.alerts.remove(alert);
    }




    public void updateInventoryItem(int newQuantity, int id){
        mapInventory.get(id).setQuantity(newQuantity);

        if(mapInventory.get(id).getQuantity() < mapInventory.get(id).getThresholdLevel()){
            for (Alert alert : this.alerts) {
                alert.alert(mapInventory, id);
            }
        }
    }

    public void addInvetoryIntem(InventoryItem inventoryItem){
        id++;
        mapInventory.put(id, inventoryItem);
    }


    public void modifyInventoryItemNameById(int id, String name){
        mapInventory.get(id).setName(name);


    }

    public void modifyInventoryItemQuantityById(int id, int quantity){
        mapInventory.get(id).setQuantity(quantity);
    }

    public void modifyInventoryItemThresholdLevelById(int id, int thresholdLevel){
        mapInventory.get(id).setThresholdLevel(thresholdLevel);
    }



    public InventoryItem retrieveInventoryDetails(int id){
        return mapInventory.get(id);
    }

}
