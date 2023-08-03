package Order;

import menu.MenuItem;

public class OrderItem {

    private MenuItem menuItem;

    private int quantity;


    public MenuItem getMenuItem(){
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }


    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuItem=" + menuItem +
                ", quantity=" + quantity +
                '}';
    }
}
