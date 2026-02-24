package core.basesyntax;

import core.basesyntax.parser.DataConverter;
import core.basesyntax.parser.DataConverterImpl;
import core.basesyntax.transaction.FruitTransaction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Feel free to remove this class and create your own.
 */
public class Main {
    // HINT: In the `public static void main(String[] args)` it is better to create
    // instances of your classes,
    // and call their methods, but do not write any business logic in the `main` method!

    public static void main(String[] arg) throws IOException {
        // 1. Read the data from the input CSV file
        List<String> lines = Files.readAllLines(Path.of("data.csv"));
        // 2. Convert the incoming data into FruitTransactions list
        DataConverter converter = new DataConverterImpl();
        List<FruitTransaction> transactions
                = (List<FruitTransaction>) converter.convertToTransaction(lines);
        // 3. Create and feel the map with all OperationHandler implementations
        // 4. Process the incoming transactions with applicable OperationHandler implementations
        for (FruitTransaction tx : transactions) {
            tx.execute();
        }
        // 5.Generate report based on the current Storage state
        // 6. Write the received report into the destination file
    }
}
