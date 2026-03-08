package core.basesyntax.parser;

import core.basesyntax.transaction.FruitTransaction;
import java.util.ArrayList;
import java.util.List;

//TODO: Повернути List<FruitTransaction> замість List<?> в сигнатурі й реалізації.
//TODO: Обробляти NumberFormatException з інформативним повідомленням (рядок/індекс).
public class DataConverterImpl implements DataConverter {
    @Override
    public List<?> convertToTransaction(Iterable<String> strings) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (String s : strings) {
            String[] split = s.trim().split(",");
            String code = split[0].trim();
            String fruit = split[1].trim();
            Integer q = Integer.parseInt(split[2].trim());
            if ("type".equalsIgnoreCase(code)) {
                continue; // skip header
            }
            transactions.add(new FruitTransaction(code, fruit, q));
        }
        return transactions;
    }
}
