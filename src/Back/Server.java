package Back;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Server {

    public static MenuStore getMenuStore(String fileType){
        switch (fileType){
            case "txt":
                return new TxtMenu();
            case "json":
                return new JsonMenu();
            case "xml":
                return new XmlMenu();
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }
    }

    public static List<MenuItems> getItemsForRestaurant(String restaurantName) throws IOException {
        String fileType = checkFileType();
        MenuStore store = getMenuStore(fileType);
        return store.dataToServer(restaurantName);
    }

    public static String checkFileType() throws IOException {
        List<String> extensions = new ArrayList<>();
        Files.walk(Paths.get("src/assets/Data"))
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    String fileName = path.getFileName().toString();
                    int dotIndex=fileName.lastIndexOf(".");
                    if(dotIndex>0){
                        String extension = fileName.substring(dotIndex+1).toLowerCase();
                        extensions.add(extension);
                    }
                });
        return extensions.get(0);
    }

    public void save(String name, MenuItems item ) throws IOException {
        String fileType = checkFileType();
        MenuStore store = getMenuStore(fileType);
        store.save(name, item);
    }

    public Boolean deleteItem(String restaurantName, String foodName) throws IOException {
        String fileType = checkFileType();
        MenuStore store = getMenuStore(fileType);
        return store.deleteItem(restaurantName, foodName);
    }
}
