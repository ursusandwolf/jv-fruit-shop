package core.basesyntax.transaction.handler;

import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.Transaction;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    BALANCE("b", new BalanceOperationHandler()),
    SUPPLY("s", new SupplyOperationHandler()),
    PURCHASE("p", new PurchaseOperationHandler()),
    RETURN("r", new ReturnOperationHandler());

    private static final Map<String, Operation> BY_CODE = new HashMap<>();
    static {
        for (Operation op : values()) {
            BY_CODE.put(op.code, op);
        }
    }

    private final String code;
    private final OperationHandler handler;

    Operation(String code, OperationHandler handler) {
        this.code = code;
        this.handler = handler;
    }

    public static Operation fromCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Operation code is empty");
        }
        Operation op = BY_CODE.get(code.trim().toLowerCase());
        if (op == null) {
            throw new IllegalArgumentException("Unknown operation code: " + code);
        }
        return op;
    }

    public String getCode() {
        return code;
    }

    public OperationHandler getHandler() {
        return handler;
    }
}
