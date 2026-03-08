package core.basesyntax.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.transaction.FruitTransaction;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {

    private DataConverterImpl converter;

    @BeforeEach
    void setUp() {
        converter = new DataConverterImpl();
    }

    @Test
    void convert_ShouldParseSingleLine() {
        List<String> input = List.of("b,apple,10");
        List<?> result = converter.convertToTransaction(input);

        assertEquals(1, result.size());

        FruitTransaction tx = (FruitTransaction) result.get(0);
        assertEquals("b", FruitTransaction.Operation.BALANCE.getCode());
        assertEquals("apple", tx.getFruit());
        assertEquals(10, tx.getQuantity());
    }

    @Test
    void convert_ShouldSkipHeader() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,apple,10"
        );
        List<?> result = converter.convertToTransaction(input);
        assertEquals(1, result.size());
    }

    @Test
    void convert_ShouldSkipHeader_IgnoringCase() {
        List<String> input = List.of(
                "TyPe,fruit,quantity",
                "b,apple,10"
        );
        List<?> result = converter.convertToTransaction(input);
        assertEquals(1, result.size());
    }

    @Test
    void convert_ShouldTrimValues() {
        List<String> input = List.of("  b  ,  apple  ,  10  ");
        List<?> result = converter.convertToTransaction(input);
        FruitTransaction tx = (FruitTransaction) result.get(0);

        assertEquals("b", FruitTransaction.Operation.BALANCE.getCode());
        assertEquals("apple", tx.getFruit());
        assertEquals(10, tx.getQuantity());
    }

    @Test
    void convert_ShouldThrowNumberFormatException_WhenQuantityInvalid() {
        List<String> input = List.of("b,apple,ten");
        assertThrows(NumberFormatException.class,
                () -> converter.convertToTransaction(input));
    }

    @Test
    void convert_ShouldThrowArrayIndexOutOfBounds_WhenColumnsMissing() {
        List<String> input = List.of("b,apple");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> converter.convertToTransaction(input));
    }

    @Test
    void convert_ShouldFail_OnEmptyLine() {
        List<String> input = List.of("");
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> converter.convertToTransaction(input));
    }

    @Test
    void convert_ShouldThrowNullPointer_WhenLineIsNull() {
        List<String> input = new ArrayList<>();
        input.add(null);
        assertThrows(NullPointerException.class,
                () -> converter.convertToTransaction(input));
    }

    @Test
    void convert_ShouldThrowNullPointer_WhenIterableIsNull() {
        assertThrows(NullPointerException.class,
                () -> converter.convertToTransaction(null));
    }
}
