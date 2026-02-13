package core.basesyntax.transaction;

public enum Transaction {
    BALANCE("b"),
    SUPPLY("s"),
    PURCHASE("p"),
    RETURN("r");

    private String code;

    Transaction(String s) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}