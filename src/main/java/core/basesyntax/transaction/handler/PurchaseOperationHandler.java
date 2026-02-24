package core.basesyntax.transaction.handler;

import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.Transaction;

public class PurchaseOperationHandler implements OperationHandler {

    @Override
    public void handle(FruitTransaction ft, Transaction tx) {
        tx.substrate(ft.getFruit(), ft.getQuantity());
    }
}
