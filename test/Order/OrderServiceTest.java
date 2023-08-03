package Order;

import menu.MenuItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService orderService = new OrderService();
    @Test
    void placeOrder() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setName("test1Item");
        menuItem.setDescription("test1description");
        menuItem.setPrice(23);
        menuItem.setAvailability(true);
        menuItem.setCategory("abc");
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);
        orderItem.setMenuItem(menuItem);
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setDate(LocalDate.of(2010, 1, 10));
        order.setCustomerName("Test");
        order.setTableNumber(1);

        orderService.placeOrder(order);

        assertSame(order, orderService.getMapTable().get(1));

    }

    @Test
    void retrieveOrderDetails() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setName("test1Item");
        menuItem.setDescription("test1description");
        menuItem.setPrice(23);
        menuItem.setAvailability(true);
        menuItem.setCategory("abc");
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);
        orderItem.setMenuItem(menuItem);
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setDate(LocalDate.of(2010, 1, 10));
        order.setCustomerName("Test");
        order.setTableNumber(1);


        System.out.println(orderService.getMapTable());
        orderService.placeOrder(order);
        Order testOrder = orderService.retrieveOrderDetails(2);
        assertSame(order, testOrder);

    }

}