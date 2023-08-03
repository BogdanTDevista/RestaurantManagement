package Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderDisplay {

    public OrderDisplay(){};
    public void displayOrder(OrderService orderService, int id){
        Order orderToDisplay = orderService.retrieveOrderDetails(id);
        List<Order> orders = new ArrayList<Order>();
        orders.add(orderToDisplay);
        printOrderDetailsAccordingly(orders);
    }

    public void displayOrders(OrderService orderService){
        List<Order> orders = new ArrayList<Order>();
        orders = orderService.getMapTable().values().stream().toList();
        printOrderDetailsAccordingly(orders);
    }
    public void displayOrdersSorted(OrderService orderService, String criteria){
        List<Order> sortedOrders = null;
        switch(criteria){
            case "date":
                sortedOrders =  orderService.getMapTable().values().stream().sorted(Comparator.comparing(Order::getDate)).toList();
                break;
            case "tblnr":
                sortedOrders =  orderService.getMapTable().values().stream().sorted(Comparator.comparingDouble(Order::getTableNumber)).toList();
                break;
            default:
                sortedOrders = orderService.getMapTable().values().stream().toList();
                System.out.println("Please select a proper criteria. Nothing has been changed");
        }
        printOrderDetailsAccordingly(sortedOrders);
    }

    public List<Order> displayFilterOrdersByStatus(OrderService orderService, Status status){
        List<Order> fillteredOrders = null;
        switch (status){
            case PENDING :
                fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() == Status.PENDING).toList();
                break;
            case COMPLETED:
                fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() == Status.COMPLETED).toList();
                break;
            case IN_PROGRESS:
                fillteredOrders = orderService.getMapTable().values().stream().filter(order -> order.getStatus() == Status.IN_PROGRESS).toList();
                break;
        }
        printOrderDetailsAccordingly(fillteredOrders);
        return fillteredOrders;
    }


    public void printOrderDetailsAccordingly(List<Order> orders) {
        for (Order order : orders) {
            System.out.println("TBL NR:" + order.getTableNumber() + "\nCustomer:" + order.getCustomerName() + "\nDate: " + order.getDate()
                    + "\nStatus of the order:" + order.getStatus() + "\nOrder items:");
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem);
            }

        }
    }
}
