package core.basesyntax.parser;

import core.basesyntax.transaction.FruitTransaction;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<?> convertToTransaction(Iterable<String> strings) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.trim().split(",");
            String code = split[0];
            String fruit = split[1];
            Integer q = Integer.parseInt(split[2]);
            transactions.add(new FruitTransaction(code, fruit, q));
        }
        return transactions;
    }
}
