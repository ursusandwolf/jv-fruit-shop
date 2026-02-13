package core.basesyntax.storage;

import java.util.HashMap;
import java.util.Map;

public class ShopStorage implements Storage {
    private final Map<String, Integer> storage = new HashMap<>();

    public boolean create(String fruit, Integer quantity) {
        // null check? negative balance?
        if (storage.containsKey(fruit)) {
            return false;
        }
        storage.put(fruit, quantity);
        return true;
    }
    // add instead of update method
    public Integer add(String fruit, Integer quantity) {
        // error or create new?
        if (!storage.containsKey(fruit)) {
            if (create(fruit, quantity)) {
                return quantity;
            } else {
                return null; // or throw Exception?
            }
        }
        Integer newValue = storage.get(fruit) + quantity;
        storage.put(fruit, newValue);
        return newValue;
    }

    public boolean delete(String fruit) {
        return false; // yet not working
    }
}
