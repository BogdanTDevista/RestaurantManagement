package Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private int tableNumber;
    private LocalDate date;
    private Status status;
    private List<OrderItem> orderItems = new ArrayList<>();


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTableNumber(){
        return tableNumber;
    }

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public List<OrderItem> getOrderItems(){
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus(){
        return status;
    }

    public void addItemsToOrder(OrderItem orderItem){
        orderItems.add(orderItem);
    }


    public double getTotalOrderValue(){
        double totalValue = 0;
        for(OrderItem orderItem : orderItems){
            totalValue += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
        }
        return totalValue ;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", tableNumber=" + tableNumber +
                ", date='" + date + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
