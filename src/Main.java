import Order.*;
import Order.Order;
import menu.MenuDisplay;
import menu.MenuItem;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

//        MenuDisplay menuDisplay = new MenuDisplay();
//
//        while(true){
//            menuDisplay.showPrincipalMenu();
//            System.out.println("Please select an option ( Ex 1 for FOOD)");
//            System.out.println("Or type 0 for exit");
//            System.out.println();
//            Scanner stringScanner = new Scanner(System.in);
//            String option = stringScanner.next();
//            switch(option){
//                case "0":
//                    return;
//                case "1":
//                    menuDisplay.showMenuItemsByCategory(1);
//                    break;
//                case "2":
//                    menuDisplay.showMenuItemsByCategory(2);
//                    break;
//                case "3":
//                    menuDisplay.showMenuItemsByCategory(3);
//                    break;
//                default:
//                    System.out.println("Please select one of the options");
//            }
//
//            String option2 = stringScanner.next();
//            if (option2.equals("0")){
//                return;
//            }
//
//        }









        // Order

        // First Order
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setName("branza");
        menuItem.setDescription("de burduf");
        menuItem.setAvailability(true);
        menuItem.setPrice(23);
        menuItem.setCategory("1");

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(2);

        Order order = new Order();
        order.setDate(LocalDate.of(2023, 2, 25));
        order.setCustomerName("Mihai");
        order.setTableNumber(5);
        order.addItemsToOrder(orderItem);


        OrderService orderService = new OrderService();
//        System.out.println(orderServic);
        Kitchen kitchen = new Kitchen();
        orderService.addObserver(kitchen);
        orderService.placeOrder(order);

        // Order2



        // Order
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(2);
        menuItem2.setName("VIN");
        menuItem2.setDescription("De Porto");
        menuItem2.setAvailability(true);
        menuItem2.setPrice(23);
        menuItem2.setCategory("2");

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setMenuItem(menuItem2);
        orderItem2.setQuantity(2);

        Order order2 = new Order();
        order2.setDate(LocalDate.of(2022, 2, 25));
        order2.setCustomerName("Alex");
        order2.setTableNumber(2);
        order2.addItemsToOrder(orderItem);
        order2.addItemsToOrder(orderItem2);

        orderService.placeOrder(order2);
        order2.setStatus(Status.COMPLETED);


        OrderDisplay orderDisplay = new OrderDisplay();
        KitchenDisplay kitchenDisplay = new KitchenDisplay();


        boolean managerMode = false;
        boolean kitchenMode = false;

        while (true){
            System.out.println("Choose a role: customer, manager, kitchen" );
            System.out.println("Type 0 for exit");
            Scanner stringScanner = new Scanner(System.in);
            String option = stringScanner.next();

            switch(option){
                case "manager":
                    orderDisplay.displayOrders(orderService);
                    managerMode = true;
                    while (managerMode) {
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
                                    CompleteOnCommand completeOnCommand = new CompleteOnCommand(kitchenDisplay, orderService, Integer.parseInt(optionkitchen));
                                    completeOnCommand.execute();
                                   // kitchenDisplay.markCompleteOrderStatus(orderService, Integer.parseInt(optionkitchen));
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
}