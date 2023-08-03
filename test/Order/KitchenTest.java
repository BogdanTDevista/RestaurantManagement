package Order;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KitchenTest {

    @Test
    void update() {
        Order order = new Order();
        OrderService orderService = new OrderService();

        Kitchen testClass = Mockito.mock(Kitchen.class);
        Map<Integer, Order> map = new HashMap<>();

        orderService.addObserver(testClass);
        orderService.placeOrder(order);
        map.put(1, order);
        Mockito.verify(testClass, Mockito.times(1)).update(map);
    }
}