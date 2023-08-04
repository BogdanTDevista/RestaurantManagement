import Inventory.InventoryDisplay;
import Inventory.InventoryItem;
import Inventory.InventoryService;
import Inventory.LowStockAlert;
import Order.*;
import Order.Order;
import menu.MenuDisplay;
import menu.MenuItem;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Services
        OrderService orderService = new OrderService();
        InventoryService inventoryService = new InventoryService();

        // Notifications
        LowStockAlert alert = new LowStockAlert();
        Kitchen kitchen = new Kitchen();

        // Display
        OrderDisplay orderDisplay = new OrderDisplay();
        KitchenDisplay kitchenDisplay = new KitchenDisplay();
        InventoryDisplay inventoryDisplay = new InventoryDisplay();

        orderService.addObserver(kitchen);
        inventoryService.addObserver(alert);

        // Set Menu Items
        MenuItem menuItem1 = createMenuItem(1, "Pizza", "Prosciutto", true, 23, "1");
        MenuItem menuItem2 = createMenuItem(2, "Paste", "Bolognese", true, 20, "1");
        MenuItem menuItem3 = createMenuItem(3, "WINE", "RED", true, 50, "2");
        MenuItem menuItem4 = createMenuItem(4, "APEROL", "SPRITZ", true, 45, "2");
        MenuItem menuItem5 = createMenuItem(5, "IC GELATO", "COCOA", true, 10, "3");

        // Set Inventory Items

        InventoryItem inventoryItem1 = createInventoryItem(menuItem1.getName(), menuItem1.getId(), 10, 3);
        InventoryItem inventoryItem2 = createInventoryItem(menuItem2.getName(), menuItem2.getId(), 12, 4);
        InventoryItem inventoryItem3 = createInventoryItem(menuItem3.getName(), menuItem3.getId(), 14, 5);
        InventoryItem inventoryItem4 = createInventoryItem(menuItem4.getName(), menuItem4.getId(), 16, 6);
        InventoryItem inventoryItem5 = createInventoryItem(menuItem5.getName(), menuItem5.getId(), 20, 7);

        inventoryService.addInvetoryIntem(inventoryItem1);
        inventoryService.addInvetoryIntem(inventoryItem2);
        inventoryService.addInvetoryIntem(inventoryItem3);
        inventoryService.addInvetoryIntem(inventoryItem4);
        inventoryService.addInvetoryIntem(inventoryItem5);



        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem1);
        orderItem.setQuantity(6);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setMenuItem(menuItem2);
        orderItem2.setQuantity(4);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setMenuItem(menuItem3);
        orderItem3.setQuantity(12);


        Order order = new Order();
        order.setDate(LocalDate.of(2023, 2, 25));
        order.setCustomerName("Mihai");
        order.setTableNumber(5);
        order.addItemsToOrder(orderItem);


        Order order2 = new Order();
        order2.setDate(LocalDate.of(2022, 2, 25));
        order2.setCustomerName("Alex");
        order2.setTableNumber(2);
        order2.addItemsToOrder(orderItem2);
        order2.addItemsToOrder(orderItem3);

        Order order3 = new Order();
        order3.setDate(LocalDate.of(2021, 2, 25));
        order3.setCustomerName("BOBO");
        order3.setTableNumber(3);
        order3.addItemsToOrder(orderItem3);


        orderService.placeOrder(order, inventoryService);

        orderService.placeOrder(order2, inventoryService);

        orderService.placeOrder(order3, inventoryService);
        //inventoryDisplay.displayInventoryItems(inventoryService);


        boolean managerMode = false;
        boolean kitchenMode = false;
        boolean optionOrderInventorymode = true;

        while (true){
            System.out.println("Choose a role: customer, manager, kitchen" );
            System.out.println("Type 0 for exit");
            Scanner stringScanner = new Scanner(System.in);
            String option = stringScanner.next();


            switch(option){

                case "manager":
                    managerMode = true;
                    while (managerMode) {
                        System.out.println("Choose to see either orders or inventory items");
                        System.out.println("Or choose b to go back");
                        String optionOrdersOrInventory = stringScanner.next();
                      //  while(optionOrderInventorymode) {
                            switch (optionOrdersOrInventory) {
                                case "orders":
                                    orderDisplay.displayOrders(orderService);
                                    System.out.println("Choose to sort or filter the order");
                                    System.out.println("Or choose b to go back");
                                    String optionFilterOrSort = stringScanner.next();

                                    switch (optionFilterOrSort) {
                                        case "filter":
                                            System.out.println("Choose the status of the order that you want to see:");
                                            String optionFillter = stringScanner.next();
                                            switch (optionFillter) {
                                                case "PENDING":
                                                    orderDisplay.displayFilterOrdersByStatus(orderService, Status.PENDING);
                                                    break;
                                                case "COMPLETED":
                                                    orderDisplay.displayFilterOrdersByStatus(orderService, Status.COMPLETED);
                                                    break;
                                                case "IN_PROGRESS":
                                                    orderDisplay.displayFilterOrdersByStatus(orderService, Status.IN_PROGRESS);
                                                    break;
                                                default:
                                                    System.out.println("PLEASE CHOOSE A PROPER STATUS!");
                                                    break;
                                            }
                                            break;
                                        case "sort":
                                            System.out.println("Choose criteria to sort");
                                            String optionSort = stringScanner.next();
                                            switch (optionSort) {
                                                case "DATE":
                                                    orderDisplay.displayOrdersSorted(orderService, "date");
                                                    break;
                                                case "TABLENR":
                                                    orderDisplay.displayOrdersSorted(orderService, "tblnr");
                                                    break;
                                                default:
                                                    System.out.println("PLEASE CHOOSE A PROPER SORT!");
                                                    break;
                                            }
                                            break;
                                        case "b":
                                            managerMode = false;
                                            break;
                                        default:
                                            System.out.println("DO NOTHING");
                                            break;
                                    }
                                case "b":
                                    managerMode = false;
                                    break;
                                case "inventory":
                                    inventoryDisplay.displayInventoryItems(inventoryService);
                                    System.out.println("Choose either to modify an inventory item, order or sort all of them");
                                    String optionModifyOrSortOrOrder = stringScanner.next();
                                    switch (optionModifyOrSortOrOrder)
                                    {
                                        case "modify":
                                            System.out.println("Choose what Item to modify");
                                            String inventoryId = stringScanner.next();
                                            System.out.println("Choose field of the Item "+ inventoryId+ " to modify modify");
                                            String field = stringScanner.next();
                                            switch (field){
                                                case "name":
                                                    System.out.println("What new name would you like?");
                                                    String name = stringScanner.next();
                                                    inventoryService.modifyInventoryItemNameById(Integer.parseInt(inventoryId), name);
                                                    break;
                                                case "quantity":
                                                    System.out.println("What new quantity would you like?");
                                                    int quantity = Integer.parseInt(stringScanner.next());
                                                    inventoryService.modifyInventoryItemQuantityById(Integer.parseInt(inventoryId), quantity);
                                                    break;
                                                case "thresholdlevel":
                                                    System.out.println("What new threshold would you like?");
                                                    int thresholdLevel =  Integer.parseInt(stringScanner.next());
                                                    inventoryService.modifyInventoryItemThresholdLevelById(Integer.parseInt(inventoryId), thresholdLevel);
                                                    break;
                                                default:
                                                    System.out.println("Please select a proper command");
                                                    break;
                                            }
                                            break;
                                    }
                            }
                      //  }


                    }
                    break;

                case "kitchen":
                    kitchenMode = true;
                    while (kitchenMode) {
                        boolean haveValues = kitchenDisplay.displayPendingOrders(orderService);
                        if(haveValues){
                            System.out.println("Choose order to mark as complete");
                            System.out.println("Or choose b to go back");
                            String optionkitchen = stringScanner.next();
                            switch (optionkitchen){
                                case "b":
                                    kitchenMode = false;
                                    break;
                                default:
                                    //orderService.markCompleteOrderStatus(orderService, Integer.parseInt(optionkitchen), inventoryService);
                                    CompleteOnCommand completeOnCommand = new CompleteOnCommand( orderService, Integer.parseInt(optionkitchen), inventoryService);
                                    completeOnCommand.execute();
                                    break;

                            }
                        }
                        else {
                            System.out.println("There are no pending orders");
                            kitchenMode = false;
                        }
                    }
                    break;
                case "customer":
                    menuInteraction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please select one of the options");
            }
    }

    }
    public  static void menuInteraction(){
        MenuDisplay menuDisplay = new MenuDisplay();

        //boolean menuOn = true;
        while(true){
            menuDisplay.showPrincipalMenu();
            System.out.println("Please select an option ( Ex 1 for FOOD)");
            System.out.println("Or type 0 for exit");
            System.out.println();
            Scanner stringScanner = new Scanner(System.in);
            String option = stringScanner.next();
            switch(option){
                case "0":
                   return;
                case "1":
                    menuDisplay.showMenuItemsByCategory(1);
                    break;
                case "2":
                    menuDisplay.showMenuItemsByCategory(2);
                    break;
                case "3":
                    menuDisplay.showMenuItemsByCategory(3);
                    break;
                default:
                    System.out.println("Please select one of the options");
            }

            String option2 = stringScanner.next();
            if (option2.equals("0")){
                return;
            }

        }

    }

    public static MenuItem createMenuItem(long id, String name, String description, boolean availability,
                                   double price, String category){
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setAvailability(availability);
        menuItem.setPrice(price);
        menuItem.setCategory(category);

        return menuItem;
    }
    public static InventoryItem createInventoryItem(String name, long menuItemID, int quantity, int thresholdLevel){
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setName(name);
        inventoryItem.setMenuItemID(menuItemID);
        inventoryItem.setQuantity(quantity);
        inventoryItem.setThresholdLevel(thresholdLevel);

        return inventoryItem;
    }

}