package core.basesyntax.storage;

public interface Storage {

    boolean create(String fruit, Integer quantity);

    Integer add(String fruit, Integer quantity);

    boolean delete(String fruit);
}
