package core.basesyntax.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.transaction.handler.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FruitTransactionOperationTest {

    private ShopTransaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new ShopTransaction(new ShopStorage());
    }

    @ParameterizedTest
    @CsvSource({
            "b, BALANCE",
            "s, SUPPLY",
            "p, PURCHASE",
            "r, RETURN",
            "B, BALANCE",
            " S , SUPPLY"
    })
    void fromCode_ShouldReturnCorrectOperation(String code, Operation expected) {
        assertEquals(expected, Operation.fromCode(code));
    }

    @Test
    void fromCode_ShouldThrow_WhenUnknownCode() {
        assertThrows(IllegalArgumentException.class,
                () -> Operation.fromCode("x"));
    }

    @Test
    void fromCode_ShouldThrow_WhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Operation.fromCode(null));
    }

    @Test
    void constructor_ShouldInitializeFields() {
        FruitTransaction tx = new FruitTransaction("b", "apple", 10);
        assertEquals("apple", tx.getFruit());
        assertEquals(10, tx.getQuantity());
    }

    private String uniqueKey() {
        return "fruit_" + System.nanoTime();
    }

    @Test
    void execute_Balance_ShouldInitializeStorage() {
        String key = uniqueKey();
        FruitTransaction ft = new FruitTransaction("b", key, 20);
        Strategy.execute(ft, transaction);
        assertEquals(20, transaction.get(key));
    }

    @Test
    void execute_Supply_ShouldIncreaseQuantity() {
        String key = uniqueKey();
        new FruitTransaction("b", key, 10).execute();
        new FruitTransaction("s", key, 5).execute();
        ShopTransaction check = new ShopTransaction();
        assertEquals(15, check.get(key));
    }

    @Test
    void execute_Purchase_ShouldDecreaseQuantity() {
        String key = uniqueKey();
        new FruitTransaction("b", key, 10).execute();
        new FruitTransaction("p", key, 4).execute();
        ShopTransaction check = new ShopTransaction();
        assertEquals(6, check.get(key));
    }

    @Test
    void execute_Purchase_ShouldThrow_WhenInsufficientBalance() {
        String key = uniqueKey();
        new FruitTransaction("b", key, 5).execute();
        assertThrows(IllegalStateException.class,
                () -> new FruitTransaction("p", key, 10).execute());
    }
}
