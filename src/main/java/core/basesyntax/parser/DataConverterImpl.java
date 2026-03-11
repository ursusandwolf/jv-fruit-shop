package core.basesyntax.parser;

import core.basesyntax.transaction.FruitTransaction;

import java.util.Optional;
import java.util.stream.Stream;

public class DataConverterImpl implements DataConverter {
    @Override
    public Stream<FruitTransaction> convertToTransaction(Stream<String> lines) {
        return lines
                .map(String::trim)
                .filter(s -> !s.startsWith("type"))
                .map(this::parseTransaction)
                .flatMap(Optional::stream);
    }

    private Optional<FruitTransaction> parseTransaction(String line) {
        try {
            String[] split = line.split(",");
            String code = split[0].trim();
            String fruit = split[1].trim();
            int quantity = Integer.parseInt(split[2].trim());

            return Optional.of(new FruitTransaction(code, fruit, quantity));

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
