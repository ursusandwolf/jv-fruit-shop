package core.basesyntax.transaction;

import core.basesyntax.storage.ShopStorage;
import core.basesyntax.storage.Storage;

import java.text.MessageFormat;

public class ShopTransaction implements Transaction {
    public static final String INVALID_DATA = "invalid data {0} {1}";
    Storage storage = new ShopStorage();
    @Override
    public boolean init(String item, Integer quantity) {
        if (isValid(item, quantity)) {
            return storage.create(item, quantity);
        }
        return false;
    }

    @Override
    public Integer get(String item) {
        return storage.read(item);
    }

    @Override
    public Integer add(String item, Integer quantity) {
        if (isValid(item, quantity)) {
            int lastValue = storage.read(item);
            int newValue = lastValue + quantity;
            if (storage.update(item, newValue)) {
                return newValue;
            }
        }
        return null;
    }

    @Override
    public Integer substrate(String item, Integer quantity) {
        return 0;
    }

    private static boolean isValid(String item, Integer quantity) {
        if (item != null && quantity != null && quantity >= 0) {
            return true;
        }
        throw new RuntimeException (INVALID_DATA + item + quantity));
    }
}
