package Order;

public class CompleteOnCommand implements Comand{

    KitchenDisplay kitchenDisplay;
    OrderService orderService;
    Integer index;

    public CompleteOnCommand(KitchenDisplay kitchenDisplay,OrderService orderService,  Integer index){
        this.kitchenDisplay = kitchenDisplay;
        this.orderService = orderService;
        this.index = index;
    }
    @Override
    public void execute() {
        kitchenDisplay.markCompleteOrderStatus(orderService, index);
    }
}
