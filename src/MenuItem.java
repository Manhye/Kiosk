public class MenuItem {
    private String name;
    private int price;
    private String description;

    MenuItem(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    String getName(){
        return name;
    }

    int getPrice(){
        return price;
    }

    String getDescription(){
        return description;
    }
}
