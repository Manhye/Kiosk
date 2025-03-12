package Back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Menu {
    private final List<String> categories = new ArrayList<>();
    private static HashMap<String, List<List<String>>> menus = new HashMap<>();

    public Menu(String category){
        menus = getDataOfMenu(category);
    }


    //get infos from txt files
    private static HashMap<String, List<List<String>>> getDataOfMenu(String category) {
        List<List<String>> menus = new ArrayList<>();
        String filePath = "src/assets/Data/" + category + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> menu = new ArrayList<>();
                System.out.println(line);
                String[] data = line.split("&");
                menu.add(data[0]);
                menu.add(data[1]);
                menu.add(data[2]);

                menus.add(menu);
            }
        } catch (IOException e) {
            System.err.println("File Error" + e.getMessage());
        }
        HashMap<String, List<List<String>>> menuList = new HashMap<>();
        menuList.put(category, menus);
        return  menuList;

    }


    public static HashMap<String, List<List<String>>> getItemsForRestaurant(String restaurantName) {
        menus = getDataOfMenu(restaurantName);
        return menus;
    }
}


