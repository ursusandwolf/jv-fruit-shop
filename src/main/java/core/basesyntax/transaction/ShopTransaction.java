package core.basesyntax.transaction;

import core.basesyntax.storage.ShopStorage;
import core.basesyntax.storage.Storage;

public class ShopTransaction implements Transaction {
    private static final Storage storage = new ShopStorage();
    private static final String NULL_ARG_FORMAT = "Argument '%s' must not be null";
    private static final String NEGATIVE_ARG_FORMAT = "Argument '%s' must be >= 0, but was %d";
    private static final String ERR_UPDATE_FAILED_FORMAT
            = "Failed to update item '%s' with value %d";

    @Override
    public boolean init(String item, Integer quantity) {
        validate(item, quantity);
        return storage.create(item, quantity);
    }

    @Override
    public Integer get(String item) {
        return storage.read(item);
    }

    @Override
    public Integer add(String item, Integer quantity) {
        validate(item, quantity);
        int lastValue = storage.read(item);
        int newValue = lastValue + quantity;
        if (!storage.update(item, newValue)) {
            throw new IllegalStateException(
                    String.format(ERR_UPDATE_FAILED_FORMAT, item, newValue)
            );
        }
        return newValue;
    }

    @Override
    public Integer substrate(String item, Integer quantity) {
        return 0;
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

}
