package core.basesyntax.transaction;

public class Strategy {

    public static void execute(FruitTransaction ft, Transaction tx) {
        ft.getOperation()
                .getHandler()
                .handle(ft, tx);
    }
}
