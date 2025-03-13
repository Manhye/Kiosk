package Back;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtMenu implements MenuStore{
    @Override
    public List<MenuItems> dataToServer(String restaurantName) {
        List<MenuItems> menus = new ArrayList<>();
        String filePath = "src/assets/Data/" + restaurantName + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                MenuItems items = new MenuItems(line);
                menus.add(items);
            }
        } catch (IOException e) {
            System.err.println("File Error" + e.getMessage());
        }
        return menus;
    }

    @Override
    public void save(String sRestaurantName, MenuItems item) {
        String filePath = "src/assets/Data/" + sRestaurantName + ".txt";
        String command = item.getName()+"&"+item.getPrice()+"&"+item.getDescription();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean deleteItem(String restaurantName, String foodName) {
        String filePath = "src/assets/Data/" + restaurantName + ".txt";
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isDeleted = false;

            while ((line = br.readLine()) != null) {
                int length = foodName.length();
                if (!line.substring(0, Math.min(length, line.length())).equals(foodName)) {
                    updatedLines.add(line);
                } else {
                    isDeleted = true;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : updatedLines) {
                    bw.write(updatedLine);
                    bw.newLine();
                }
            }

            return isDeleted;

        } catch (IOException e) {
            System.err.println("File Error: " + e.getMessage());
            return false;
        }
    }
}
