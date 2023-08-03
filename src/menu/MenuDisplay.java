package menu;

import java.util.ArrayList;
import java.util.List;

public class MenuDisplay {

    MenuService menuService = MenuService.getInstance();

    MenuItemService menuItemService = MenuItemService.getInstance();


    public MenuDisplay() {
        initializeDatabase();
    }

    public void showPrincipalMenu(){
        List<Menu> menus = menuService.findAll();
        int index = 1;
        for (Menu menu : menus){
            System.out.println(index + "." + menu.getCategory());
            index++;
        }
    }

    public void showMenuItemsByCategory(long mid){
        List<MenuItem> menuItems = menuItemService.findByMId(mid);
        int index = 1;
        if (menuItems.size() == 0){
            System.out.println("Unfortunately We are out of this category");
        }
        for (MenuItem menuItem : menuItems){
            System.out.println(menuItem.getName() + " DESCR:" + menuItem.getDescription() + " Price:"
                                +menuItem.getPrice() + "$ AVAILABLE:" + menuItem.getAvailability());
            index++;
        }
        System.out.println("Please type b if you want to go to the maine menu or 0 to finalize");
    }


    public void initializeDatabase(){

        // Initialize the main menu
        Menu newMenu = new Menu();
        ArrayList<String> categories = new ArrayList<>();

        List<Menu> menus = menuService.findAll();
        if (menus.size() == 0)
        {
            newMenu.setCategory("FOOD");
            menuService.add(newMenu);
            newMenu.setCategory("DRINKS");
            menuService.add(newMenu);
            newMenu.setCategory("DESSERTS");
            menuService.add(newMenu);
        }
        else {
            for (Menu menu : menus) {
                categories.add(menu.getCategory());
            }
            if(!categories.contains("FOOD")){
                newMenu.setCategory("FOOD");
                menuService.add(newMenu);
            }
            else if(!categories.contains("DRINKS")) {
                newMenu.setCategory("DRINKS");
                menuService.add(newMenu);
            }
            else if(!categories.contains("DESSERTS")) {
                newMenu.setCategory("DESSERTS");
                menuService.add(newMenu);
            }
        }

        // Initialize menu Items

        List<MenuItem> newMenuItems =new ArrayList<>();
        MenuItem newMenuItem = new MenuItem();

        // first menu item
        newMenuItem.setName("Pizza");
        newMenuItem.setDescription("Prosciutto");
        newMenuItem.setAvailability(true);
        newMenuItem.setPrice(25);
        newMenuItem.setDescription("1");
        newMenuItems.add(newMenuItem);

        newMenuItem.setName("Paste");
        newMenuItem.setDescription("Bolognese");
        newMenuItem.setAvailability(true);
        newMenuItem.setPrice(20);
        newMenuItem.setDescription("1");
        newMenuItems.add(newMenuItem);

        newMenuItem.setName("WINE");
        newMenuItem.setDescription("RED");
        newMenuItem.setAvailability(false);
        newMenuItem.setPrice(50);
        newMenuItem.setDescription("2");
        newMenuItems.add(newMenuItem);

        newMenuItem.setName("APEROL");
        newMenuItem.setDescription("SPRITZ");
        newMenuItem.setAvailability(true);
        newMenuItem.setPrice(45);
        newMenuItem.setDescription("2");
        newMenuItems.add(newMenuItem);

        newMenuItem.setName("TIRAMISU");
        newMenuItem.setDescription("LARGE");
        newMenuItem.setAvailability(true);
        newMenuItem.setPrice(15);
        newMenuItem.setDescription("3");
        newMenuItems.add(newMenuItem);

        newMenuItem.setName("IC GELATO");
        newMenuItem.setDescription("COCOA");
        newMenuItem.setAvailability(true);
        newMenuItem.setPrice(10);
        newMenuItem.setDescription("3");
        newMenuItems.add(newMenuItem);


        List<MenuItem> menuItemInDB = menuItemService.findAll();
        boolean isInList;

        for(MenuItem menuItem: newMenuItems){
            isInList = false;
            for(MenuItem menuItem1 : menuItemInDB){
                if (menuItem.getName().equals(menuItem1.getName()))
                    isInList = true;
            }
            if(!isInList){
                menuItemService.add(menuItem);
            }
        }

    }

}
