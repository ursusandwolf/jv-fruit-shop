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

    public Integer add(String fruit, Integer quantity) {
        if (!storage.containsKey(fruit)) {
            throw new IllegalArgumentException(fruit + " not exist in storage!");
        }
        Integer newValue = storage.get(fruit) + quantity;
        return update(fruit, newValue);
    }

    public Integer update(String fruit, Integer setQuantity) {
        if (!storage.containsKey(fruit)) {
            throw new IllegalArgumentException(fruit + " not exist in storage!");
        }
        storage.put(fruit, setQuantity);
        return setQuantity;
    }

    public boolean delete(String fruit) {
        return false; // yet not working
    }
}
