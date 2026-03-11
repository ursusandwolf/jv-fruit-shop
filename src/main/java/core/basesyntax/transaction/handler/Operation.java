package core.basesyntax.transaction.handler;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.ShopTransaction;
import core.basesyntax.transaction.Transaction;
import java.util.Map;

public enum Operation {
    BALANCE("b"),
    SUPPLY("s"),
    PURCHASE("p"),
    RETURN("r");

    private final String code;

    Operation(String code) {
        this.code = code;
    }

    public static Operation fromCode(String code) {
        if (code != null) {
            for (Operation op : Operation.values()) {
                if (op.getCode().equalsIgnoreCase(code.trim())) {
                    return op;
                }
            }
        }
        //Unknown operation code: null
        throw new IllegalArgumentException("Unknown operation code: " + code);
    }

    public String getCode() {
        return code;
    }
}
