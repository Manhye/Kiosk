import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<MenuItem> menuItems = new ArrayList<MenuItem>();
    private final List<String> categories = new ArrayList<>();


    public Menu() {
        menuItems.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce", "Burgers"));
        menuItems.add(new MenuItem("SmokeShack", 8900,"Cheeseburger topped with bacon, cherry peppers and ShakeSauce", "Burgers"));
        menuItems.add(new MenuItem("Cheeseburger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce", "Burgers"));
        menuItems.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with potato bun and beef patty", "Burgers"));
        menuItems.add(new MenuItem("Hamburger", 5400,"Basic burger with vegetables based on beef patty", "Burgers"));
        menuItems.add(new MenuItem("Coke", 2000, "Enjoy an ice-cold Coke for a refreshing break!","Drinks"));
        menuItems.add(new MenuItem("Sprite", 2000, "Cool down with a crisp and refreshing Sprite!","Drinks"));
        menuItems.add(new MenuItem("Orange Juice", 2500, "Fresh and fruity! Enjoy a delicious orange juice!","Drinks"));
        menuItems.add(new MenuItem("Coffee",2000,"Bold and energizing! Enjoy a fresh cup of coffee!","Drinks"));
        menuItems.add(new MenuItem("Egg Tart",3000,"Rich, creamy, and perfectly baked! Try our egg tart!","Desserts"));
        menuItems.add(new MenuItem("Sausage", 2500, "Juicy and savory! Enjoy a delicious sausage!","Desserts"));
        menuItems.add(new MenuItem("Salt Bread",4000,"Soft, buttery, and perfectly salted! Try our salt bread!","Desserts"));

        categories.add("Burgers");
        categories.add("Drinks");
        categories.add("Desserts");
    }

    public void showCategory(){
        System.out.println("[ MAIN MENU ]");
        for(int i = 0; i < categories.size(); i++){
            System.out.println((i+1)+". " + categories.get(i));
        }
        System.out.println("0. Exit");
    }

    public void showMenu(int inp){
        String cat = categories.get(inp);
        System.out.println("[ " + cat.toUpperCase() + " ]");
        int index = 1;
        for(int i = 0; i < menuItems.size(); i++){
            if(menuItems.get(i).getCategory().equals(cat)){
                System.out.println(index + ". " + menuItems.get(i).getName()+"(₩ "+menuItems.get(i).getPrice()+"): "+menuItems.get(i).getDescription());
                index++;
            }
        }
        System.out.println("0. Back");
    }

    public void selected(int inp, int inp2){
        List<MenuItem> temp = new ArrayList<>();
        for(int i = 0; i < menuItems.size(); i++){
            if(menuItems.get(i).getCategory().equals(categories.get(inp))){
                temp.add(menuItems.get(i));
            }
        }
        System.out.println("Selected Menu: "+temp.get(inp2).getName()+"(₩ "+temp.get(inp2).getPrice()+"): "+temp.get(inp2).getDescription());
    }
}
