package core.basesyntax.transaction;

import core.basesyntax.storage.ShopStorage;
import core.basesyntax.storage.Storage;

public class ShopTransaction implements Transaction {
    private static final Storage storage = new ShopStorage();
    private static final String NULL_ARG_FORMAT = "Argument '%s' must not be null";
    private static final String NEGATIVE_ARG_FORMAT = "Argument '%s' must be >= 0, but was %d";
    private static final String ERR_UPDATE_FAILED_FORMAT
            = "Failed to update item '%s' with value %d";
    private static final String ERR_CREATE_FAILED_FORMAT
            = "Failed to create item '%s' with value %d";

    public ShopTransaction() {
    }

    private static int change(String item, Integer delta) {
        int lastValue = storage.read(item);
        int newValue = lastValue + delta;
        if (!storage.update(item, newValue)) {
            throw new IllegalStateException(
                    String.format(ERR_UPDATE_FAILED_FORMAT, item, newValue)
            );
        }
        return newValue;
    }

    private static void validate(String item, Integer quantity) {
        if (item == null) {
            throw new IllegalArgumentException(
                    String.format(NULL_ARG_FORMAT, "item")
            );
        }
        if (quantity == null) {
            throw new IllegalArgumentException(
                    String.format(NULL_ARG_FORMAT, "quantity")
            );
        }
        if (quantity < 0) {
            throw new IllegalArgumentException(
                    String.format(NEGATIVE_ARG_FORMAT, "quantity", quantity)
            );
        }
    }

    @Override
    public Integer init(String item, Integer quantity) {
        validate(item, quantity);
        if (storage.create(item, quantity)) {
            return quantity;
        }
        throw new IllegalStateException(
                String.format(ERR_CREATE_FAILED_FORMAT, item, quantity));
    }

    @Override
    public Integer get(String item) {
        return storage.read(item);
    }

    @Override
    public Integer add(String item, Integer quantity) {
        validate(item, quantity);
        return change(item, quantity);
    }

    @Override
    public Integer substrate(String item, Integer quantity) {
        validate(item, quantity);
        return change(item, -1 * quantity);
    }

}
