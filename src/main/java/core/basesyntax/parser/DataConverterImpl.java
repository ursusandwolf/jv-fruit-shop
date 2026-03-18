package core.basesyntax.parser;

import core.basesyntax.transaction.FruitTransaction;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DataConverterImpl implements DataConverter {
    @Override
    public Stream<FruitTransaction> convertToTransaction(Stream<String> lines) {
        return convertToTransaction(lines, System.err::println);
    }

    public Stream<FruitTransaction> convertToTransaction(
            Stream<String> lines, Consumer<String> errorHandler) {
        AtomicInteger line = new AtomicInteger(1);

        return lines
                .map(String::trim)
                .filter(s -> !s.startsWith("type"))
                .map(s -> parseTransaction(s, line.getAndIncrement(), errorHandler))
                .flatMap(Optional::stream);
    }

    private Optional<FruitTransaction> parseTransaction(
            String line, int lineNumber) {
        return parseTransaction(line, lineNumber, System.err::println);
    }

    private Optional<FruitTransaction> parseTransaction(
            String line, int lineNumber, Consumer<String> errorHandler) {
        try {
            String[] split = line.split(",");
            String code = split[0].trim();
            String fruit = split[1].trim();
            int quantity = Integer.parseInt(split[2].trim());

            return Optional.of(new FruitTransaction(code, fruit, quantity));

        } catch (NumberFormatException e) {
            errorHandler.accept("Parse error at line " + lineNumber + ": " + line);
        } catch (Exception e) {
            errorHandler.accept("Malformed CSV at line " + lineNumber + ": " + line);
        }
        return Optional.empty();
    }
}
