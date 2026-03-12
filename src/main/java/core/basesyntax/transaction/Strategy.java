package core.basesyntax.transaction;

import core.basesyntax.transaction.handler.OperationHandler;

public class Strategy {

    public static void execute(FruitTransaction ft, Transaction tx) {
        OperationHandler handler = ft.getOperation().getHandler();
        handler.handle(ft, tx);
    }
}
