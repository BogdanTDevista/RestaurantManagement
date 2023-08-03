package menu;

import java.util.ArrayList;

public class Menu {

    ArrayList<MenuItem> menuItems = new ArrayList<>();

    public Menu(){};
    private long id;

    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItems=" + menuItems +
                ", id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
