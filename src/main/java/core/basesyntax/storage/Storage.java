package core.basesyntax.storage;

public interface Storage {

    boolean create(String fruit, Integer quantity);

    Integer update(String fruit, Integer quantity);

    boolean delete(String fruit);
}
