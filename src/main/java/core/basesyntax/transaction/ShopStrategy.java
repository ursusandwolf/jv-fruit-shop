package core.basesyntax.transaction;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.transaction.handler.BalanceOperationHandler;
import core.basesyntax.transaction.handler.Operation;
import core.basesyntax.transaction.handler.OperationHandler;
import core.basesyntax.transaction.handler.PurchaseOperationHandler;
import core.basesyntax.transaction.handler.ReturnOperationHandler;
import core.basesyntax.transaction.handler.SupplyOperationHandler;

import java.util.Map;

public class ShopStrategy {
    private static final Map<Operation, OperationHandler> strategy = Map.of(
            Operation.BALANCE, new BalanceOperationHandler(),
            Operation.SUPPLY, new SupplyOperationHandler(),
            Operation.PURCHASE, new PurchaseOperationHandler(),
            Operation.RETURN, new ReturnOperationHandler()
    );
    private Transaction tx = new ShopTransaction(new ShopStorage());


    public void execute(FruitTransaction ft) {
        OperationHandler handler = strategy.get(ft.getOperation());
        handler.handle(ft, tx);
    }
}
