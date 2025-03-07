public class MenuItem {
    private final String category;
    private final String name;
    private final int price;
    private final String description;

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
