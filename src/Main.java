import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> list = new ArrayList<MenuItem>();
        list.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce"));
        list.add(new MenuItem("SmokeShack", 8900,"Cheeseburger topped with bacon, cherry peppers and ShakeSauce"));
        list.add(new MenuItem("Cheeseburger", 6900,"Cheeseburger topped with tomato, lettuce, and ShakeSauce"));
        list.add(new MenuItem("ShackBurger", 6900,"Cheeseburger topped with potato bun and beef patty"));
        list.add(new MenuItem("Hamburger", 5400,"Basic burger with vegetables based on beef patty"));

        Scanner sc = new Scanner(System.in);
        while(true){
            for(int i = 0; i < list.size(); i++) {
                System.out.println((i+1)+". " + list.get(i).getName() + " " + list.get(i).getPrice()+ " - " + list.get(i).getDescription());
            }
            System.out.println("0. Exit -> exit");
            int inp = sc.nextInt();

            if (inp == 0){
                System.exit(0);
            }
            try{
                inp--;
                System.out.println("Purchased Item:");
                System.out.println(list.get(inp).getName()+"("+list.get(inp).getPrice()+"): "+list.get(inp).getDescription());
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