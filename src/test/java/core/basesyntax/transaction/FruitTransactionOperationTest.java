package core.basesyntax.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FruitTransactionOperationTest {

    @ParameterizedTest
    @CsvSource({
            "b, BALANCE",
            "s, SUPPLY",
            "p, PURCHASE",
            "r, RETURN",
            "B, BALANCE",
            " S , SUPPLY"
    })
    void fromCode_ShouldReturnCorrectOperation(String code,
                                               FruitTransaction.Operation expected) {
        assertEquals(expected,
                FruitTransaction.Operation.fromCode(code));
    }

    @Test
    void fromCode_ShouldThrow_WhenUnknownCode() {
        assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode("x"));
    }

    @Test
    void fromCode_ShouldThrow_WhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> FruitTransaction.Operation.fromCode(null));
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
        FruitTransaction tx = new FruitTransaction("b", key, 20);
        tx.execute();
        ShopTransaction check = new ShopTransaction();
        assertEquals(20, check.get(key));
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
