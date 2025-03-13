package Back;

import java.util.ArrayList;
import java.util.List;


public class Restaurant {
    private final List<String> categories = new ArrayList<>();

    public Restaurant(){
        categories.add("BurgerKing");
        categories.add("Bongousse");
        categories.add("IssacToast");
        categories.add("Puradak");
    }


    public List<String> getCategories() {
        return categories;
    }


}


