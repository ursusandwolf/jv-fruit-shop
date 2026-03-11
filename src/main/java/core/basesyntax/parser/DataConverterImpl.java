package core.basesyntax.parser;

import core.basesyntax.transaction.FruitTransaction;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class DataConverterImpl implements DataConverter {
    @Override
    public Stream<FruitTransaction> convertToTransaction(Stream<String> lines) {
        AtomicInteger line = new AtomicInteger(1);

        return lines
                .map(String::trim)
                .filter(s -> !s.startsWith("type"))
                .map(s -> parseTransaction(s, line.getAndIncrement()))
                .flatMap(Optional::stream);
    }

    private Optional<FruitTransaction> parseTransaction(String line, int lineNumber) {
        try {
            String[] split = line.split(",");
            String code = split[0].trim();
            String fruit = split[1].trim();
            int quantity = Integer.parseInt(split[2].trim());

            return Optional.of(new FruitTransaction(code, fruit, quantity));

        } catch (NumberFormatException e) {
            System.err.println(
                    "Parse error at line " + lineNumber +
                            ": invalid number -> " + line
            );
        } catch (Exception e) {
            System.err.println(
                    "Malformed CSV at line " + lineNumber +
                            ": " + line
            );
        }
        return Optional.empty();
    }
}
