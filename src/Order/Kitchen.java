package Order;

import Order.*;
import java.util.HashMap;
import java.util.Map;

public class Kitchen implements Channel {
    private Map<Integer, Order> mapTable = new HashMap<>();

    public Kitchen(){};

    @Override
    public void update(Map<Integer, Order> map) {
        this.setMapTable(map);
        System.out.println("A new order has been placed");

    }

    public void setMapTable(Map<Integer, Order> mapTable) {
        this.mapTable = mapTable;
    }
}
