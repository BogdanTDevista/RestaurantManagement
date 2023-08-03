package Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitchenDisplay {


    public KitchenDisplay(){

    }

    public boolean displayPendingOrders(OrderService orderService){

        List<Order> fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() == Status.PENDING).toList();
        displayOrdersForKitchen(fillteredOrders);
        if(fillteredOrders.size() == 0){
            return false;
        }
        return true;

    }

    public void markCompleteOrderStatus(OrderService orderService, int orderIndex){

        List<Order> fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() == Status.PENDING).toList();

        for(Map.Entry<Integer, Order> entry: orderService.getMapTable().entrySet()){
            if(entry.getValue() == fillteredOrders.get(orderIndex - 1)){
                entry.getValue().setStatus(Status.COMPLETED);
                return;
            }
        }
    }


    public void displayOrdersForKitchen(List<Order> orders) {
        int index = 0;
        for (Order order : orders) {
            System.out.println("Order number: " + ++index );
            System.out.println("Status of the order:" + order.getStatus() + "\nOrder items:");
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem.getQuantity() + "piece/s of: " + orderItem.getMenuItem().getName()
                        + " " + orderItem.getMenuItem().getDescription());
            }
        }
    }

}
