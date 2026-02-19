package core.basesyntax.transaction;

public class FruitTransaction {
    private Operation operation;
    private String fruit;
    private int quantity;

    public FruitTransaction(Operation operation, String fruit, int quantity) {
        this.operation = operation;
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public enum Operation {
        BALANCE("b") {
            @Override
            public int apply(String fruit, int quantity) {
                return transaction.init(fruit, quantity);
            }
        },
        SUPPLY("s") {
            @Override
            public int apply(String fruit, int quantity) {
                return transaction.add(fruit, quantity);
            }
        },
        PURCHASE("p") {
            @Override
            public int apply(String fruit, int quantity) {
                return transaction.substrate(fruit, quantity);
            }
        },
        RETURN("r") {
            @Override
            public int apply(String fruit, int quantity) {
                return transaction.add(fruit, quantity);
            }
        };

        public abstract int apply(String fruit, int quantity);

        private final static Transaction transaction = new ShopTransaction();
        private String code;

        Operation(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
