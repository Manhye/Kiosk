package Back;

import java.util.List;

public class JsonMenu implements MenuStore{
    @Override
    public List<MenuItems> dataToServer(String restaurantName) {
        return List.of();
    }

    @Override
    public void save(String sRestaurantName, MenuItems item) {

    }

    @Override
    public Boolean deleteItem(String restaurantName, String foodName) {
        return null;
    }
}
