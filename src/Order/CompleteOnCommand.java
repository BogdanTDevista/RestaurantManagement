package Order;

import Inventory.InventoryService;

public class CompleteOnCommand implements Comand{

    OrderService orderService;
    Integer index;

    InventoryService inventoryService = new InventoryService();
    public CompleteOnCommand(OrderService orderService,  Integer index, InventoryService inventoryService){
        this.orderService = orderService;
        this.index = index;
        this.inventoryService = inventoryService;
    }
    @Override
    public void execute() {
        orderService.markCompleteOrderStatus(orderService, index, inventoryService);
    }
}
