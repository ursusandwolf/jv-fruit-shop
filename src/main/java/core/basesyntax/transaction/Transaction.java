package core.basesyntax.transaction;

public interface Transaction {
    boolean init(String item, Integer quantity);

    Integer get(String item);

    Integer add(String item, Integer quantity);

    Integer substrate(String item, Integer quantity);

}
