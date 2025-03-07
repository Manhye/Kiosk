import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {


    public void init(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce"));
        menuItems.add(new MenuItem("SmokeShack", 8900,"Cheeseburger topped with bacon, cherry peppers and ShakeSauce"));
        menuItems.add(new MenuItem("Cheeseburger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce"));
        menuItems.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with potato bun and beef patty"));
        menuItems.add(new MenuItem("Hamburger", 5400,"Basic burger with vegetables based on beef patty"));

        Scanner sc = new Scanner(System.in);
        while(true){
            for(int i = 0; i < menuItems.size(); i++) {
                System.out.println((i+1)+". " + menuItems.get(i).getName() + " " + menuItems.get(i).getPrice()+ " - " + menuItems.get(i).getDescription());
            }
            System.out.println("0. Exit -> exit");
            int inp = sc.nextInt();

            if (inp == 0){
                System.exit(0);
            }
            try{
                inp--;
                System.out.println("Purchased Item:");
                System.out.println(menuItems.get(inp).getName()+"("+menuItems.get(inp).getPrice()+"): "+menuItems.get(inp).getDescription());
            }catch(IndexOutOfBoundsException e){
                System.out.println("[ERROR] Invalid Number");
            }
            System.out.println();
            System.out.println();
            System.out.println();
            sc.nextLine();
        }
    }

}
