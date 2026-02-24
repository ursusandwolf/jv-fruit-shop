package core.basesyntax.transaction.handler;

import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.Transaction;

public class BalanceOperationHandler implements OperationHandler {

    @Override
    public void handle(FruitTransaction ft, Transaction tx) {
        tx.init(ft.getFruit(), ft.getQuantity());
    }
}
