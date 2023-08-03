package menu;

public class MenuItem {
    private long id;
    private String name;

    private String description;

    private double price;

    private boolean availability;

    private String category;

    public MenuItem(){};


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getAvailability(){
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", category='" + category + '\'' +
                '}';
    }
}
