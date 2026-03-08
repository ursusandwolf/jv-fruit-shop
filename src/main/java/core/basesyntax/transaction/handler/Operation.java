package core.basesyntax.transaction.handler;

import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.ShopTransaction;
import core.basesyntax.transaction.Transaction;

import java.util.Map;

public enum Operation {
    BALANCE("b"),
    SUPPLY("s"),
    PURCHASE("p"),
    RETURN("r");

    private static final Map<Operation, OperationHandler> strategy = Map.of(
            Operation.BALANCE, new BalanceOperationHandler(),
            Operation.SUPPLY, new SupplyOperationHandler(),
            Operation.PURCHASE, new PurchaseOperationHandler(),
            Operation.RETURN, new ReturnOperationHandler()
    );
    private final String code;
    private final Transaction tx = new ShopTransaction();


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

    public void execute(FruitTransaction ft) {
        OperationHandler handler = strategy.get(this);
        handler.handle(ft, tx);
    }

    public String getCode() {
        return code;
    }
}
