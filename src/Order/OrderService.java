package Order;

import Inventory.InventoryItem;
import Inventory.InventoryService;

import java.util.*;

public class OrderService {


    // Creating an empty HashMap
    private Map<Integer, Order> mapTable = new HashMap<>();

    private static int id = 0;
    private List<Channel> channels = new ArrayList<>();


    public OrderService(){
    }


    public Map<Integer, Order> getMapTable(){
        return mapTable;
    }

    public void addObserver(Channel channel) {
        this.channels.add(channel);
    }

    public void removeObserver(Channel channel) {
        this.channels.remove(channel);
    }


    public void placeOrder(Order order, InventoryService inventoryService){

        // check if the items are in the inventory
        List<OrderItem> newOrderList = new ArrayList<>();
        for(OrderItem orderItem : order.getOrderItems()){
            for(InventoryItem inventoryItem :inventoryService.getInventoryMap().values()){
                if(orderItem.getMenuItem().getName() == inventoryItem.getName()){
                    if(orderItem.getQuantity() > inventoryItem.getQuantity()){
                        System.out.println("Sorry, we don't have so much" + inventoryItem.getName() + " in the inventory");
                    }
                    else {
                        newOrderList.add(orderItem);
                    }
                }
            }
        }

        if(newOrderList.size() == 0)
            return;
        order.setOrderItems(newOrderList);

        id++;
        order.setStatus(Status.PENDING);
        mapTable.put(id, order);

        for (Channel channel : this.channels) {
            channel.update(this.mapTable);
        }
    }

    public void markCompleteOrderStatus(OrderService orderService, int orderIndex, InventoryService inventoryService ){

        List<Order> fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() != Status.COMPLETED).toList();

        for(Map.Entry<Integer, Order> entry: orderService.getMapTable().entrySet()){
            if(entry.getValue() == fillteredOrders.get(orderIndex - 1)){

                for(OrderItem orderItem: entry.getValue().getOrderItems()){
                    for(Map.Entry<Integer, InventoryItem> entryInventory: inventoryService.getInventoryMap().entrySet()){
                        if(entryInventory.getValue().getName() == orderItem.getMenuItem().getName()){
                            if(entryInventory.getValue().getQuantity() >= orderItem.getQuantity()){
                                entry.getValue().setStatus(Status.COMPLETED);
                                int newQuantity = entryInventory.getValue().getQuantity() - orderItem.getQuantity();
                                inventoryService.updateInventoryItem(newQuantity,entryInventory.getKey());
                            }
                            else{
                                System.out.println("We don't have enough pieces of " + entryInventory.getValue().getName());
                            }
                        }
                    }
                }
            }
        }
    }

    public Order retrieveOrderDetails(int id){
        return mapTable.get(id);
    };

    public void updateStatusOfOrder(Order order, Status status){
        order.setStatus(status);
    }



}
