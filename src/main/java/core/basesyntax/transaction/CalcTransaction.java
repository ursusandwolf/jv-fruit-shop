package core.basesyntax.transaction;

public class CalcTransaction {
    private Transaction transaction;
    private Integer quantity;
    private Integer balance;

    public CalcTransaction(Transaction t, Integer b, Integer q) {
        this.transaction = t;
        this.balance = b;
        this.quantity = q;
    }

    //bspr
    /*b - set, s - add, p - sub, r - add*/
    public Integer get() {
        switch (transaction) {
            case BALANCE :
                return quantity;
            case SUPPLY, RETURN:
                return balance + quantity;
            case PURCHASE:
                return balance - quantity;
        }
        return null;
    }
}
