package Back;

import java.util.*;

public interface MenuStore {

    List<MenuItems> dataToServer(String restaurantName);
    void save(String sRestaurantName, MenuItems item);
    default List<MenuItems> getItemsForRestaurant(String restaurantName){
        return dataToServer(restaurantName);
    }
    Boolean deleteItem(String restaurantName, String foodName);
}
