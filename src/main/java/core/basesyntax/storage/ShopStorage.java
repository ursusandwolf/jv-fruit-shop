package core.basesyntax.storage;

import java.util.HashMap;
import java.util.Map;

public class ShopStorage implements Storage {
    private final Map<String, Integer> storage = new HashMap<>();

    // accept any data, even null key and value
    public boolean create(String fruit, Integer quantity) {
        if (storage.containsKey(fruit)) {
            return false;
        }
        storage.put(fruit, quantity);
        return true;
    }

    public Integer read(String key) {
        return storage.get(key);
    }

    // add or substrate in transaction layer
    public boolean update(String fruit, Integer quantity) {
        if (!storage.containsKey(fruit)) {
            throw new IllegalArgumentException(fruit + " not exist in storage!");
        }
        storage.put(fruit, quantity);
        return true;
    }

    public boolean delete(String fruit) {
        if (!storage.containsKey(fruit)) {
            throw new IllegalArgumentException(fruit + " not exist in storage!");
        }
        storage.remove(fruit);
        return true;
    }
}
