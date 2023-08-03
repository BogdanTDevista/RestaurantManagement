package Order;

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


    public void placeOrder(Order order){
        id++;
        order.setStatus(Status.PENDING);
        mapTable.put(id, order);

        for (Channel channel : this.channels) {
            channel.update(this.mapTable);
        }
    }

    public Order retrieveOrderDetails(int id){
        return mapTable.get(id);
    };

    public void updateStatusOfOrder(Order order, Status status){
        order.setStatus(status);
    }


}
