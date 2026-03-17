package core.basesyntax.transaction;

import core.basesyntax.transaction.handler.OperationHandler;

public class Strategy {

    public static void execute(FruitTransaction ft, Transaction tx) {
        ft.getOperation()
                .getHandler()
                .handle(ft, tx);
    }
}
