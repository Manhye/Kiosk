package Back;

public class MenuItems {
    private String name;
    private Integer price;
    private String description;

    public MenuItems(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public MenuItems(String line){
        String[] data = line.split("&");
        this.name =data[0];
        this.price = Integer.valueOf(data[1]);
        this.description =data[2];
    }


    public String getName(){
        return name;
    }

    public Integer getPrice(){
        return price;
    }
    public String getDescription(){
        return description;
    }

}
