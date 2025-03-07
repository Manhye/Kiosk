public class MenuItem {
    private String category;
    private String name;
    private int price;
    private String description;

    MenuItem(String name, int price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
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

    String getCategory(){
        return category;
    }

}
