import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {


    public void init(){

        Scanner sc = new Scanner(System.in);
        while(true){
            Menu menu = new Menu();
            menu.showCategory();
            int inp = sc.nextInt();
            if(inp==0){
                System.out.println("Exit Program");
                System.exit(0);
            }
            try{
                menu.showMenu(inp-1);
            }catch(IndexOutOfBoundsException e){
                System.out.println("[ERROR] Invalid Number");
                continue;
            }

            sc.nextLine();

            int inp2 = sc.nextInt();

            if(inp2==0){
                continue;
            }
            try{
                menu.selected(inp-1, inp2-1);
            }catch(IndexOutOfBoundsException e){
                System.out.println("[ERROR] Invalid Number");
            }

        }
    }

}
