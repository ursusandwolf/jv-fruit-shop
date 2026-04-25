package core.basesyntax.transaction;

import core.basesyntax.strategy.handler.Operation;

public class FruitTransaction {

    private Operation operation;
    private String fruit;
    private int quantity;

    public FruitTransaction(String code, String fruit, int quantity) {
        this.operation = Operation.fromCode(code);
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getFruit() {
        return fruit;
    }

    public int getQuantity() {
        return quantity;
    }
}
