import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("[ SHAKESHACK MENU ]\n" +
                    "1. ShackBurger   | W 6.9 | Cheeseburger topped with tomato, lettuce, and ShakeSauce\n" +
                    "2. SmokeShack    | W 8.9 | Cheeseburger topped with bacon, cherry peppers and ShakeSauce\n" +
                    "3. Cheeseburger  | W 6.9 | Cheeseburger topped with potato bun and beef patty\n" +
                    "4. Hamburger     | W 5.4 | Basic burger with vegetables based on beef patty\n" +
                    "0. Exit          | exit");

            String inp = sc.nextLine();
            switch(inp){
                case "1":
                    System.out.println("ShackBurger Purchased!");
                    System.out.println();
                    System.out.println();
                    break;
                case "2":
                    System.out.println("SmokeShack Purchased!");
                    System.out.println();
                    System.out.println();
                    break;
                case "3":
                    System.out.println("Cheeseburger Purchased!");
                    System.out.println();
                    System.out.println();
                    break;
                case "4":
                    System.out.println("Hamburger Purchased!");
                    break;
                case "0":
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    System.out.println();
                    System.out.println();
                    break;
            }


        }
    }

}