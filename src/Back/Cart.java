package Back;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, Integer> cartItems;
    private final Map<String, Integer> itemPrices;

    public Cart() {
        cartItems = new HashMap<>();
        itemPrices = new HashMap<>();
    }

    public void addItem(String itemName, int price) {
        cartItems.put(itemName, cartItems.getOrDefault(itemName, 0) + 1);
        itemPrices.put(itemName, price);
    }

    public void removeItem(String itemName) {
        if (cartItems.containsKey(itemName)) {
            int count = cartItems.get(itemName);
            if (count > 1) {
                cartItems.put(itemName, count - 1);
            } else {
                cartItems.remove(itemName);
                itemPrices.remove(itemName);
            }
        }
    }

    public void deleteItem(String itemName) {
        cartItems.remove(itemName);
        itemPrices.remove(itemName);
    }

    public Map<String, Integer> getCartItems() {
        return cartItems;
    }

    public int getItemPrice(String itemName) {
        return itemPrices.getOrDefault(itemName, 0);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            total += itemPrices.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    public void clear(){
        cartItems.clear();
    }

}
