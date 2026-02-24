package core.basesyntax.transaction.handler;

import core.basesyntax.transaction.FruitTransaction;
import core.basesyntax.transaction.Transaction;

public interface OperationHandler {
    void handle(FruitTransaction ft, Transaction tx);
}
