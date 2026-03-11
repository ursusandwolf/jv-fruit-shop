package core.basesyntax;

import core.basesyntax.inout.CsvWriter;
import core.basesyntax.inout.LineReader;
import core.basesyntax.inout.LineReaderFactory;
import core.basesyntax.inout.ReaderType;
import core.basesyntax.inout.ReportWriter;
import core.basesyntax.parser.DataConverter;
import core.basesyntax.parser.DataConverterImpl;
import core.basesyntax.report.ReportGenerator;
import core.basesyntax.report.ReportGeneratorImpl;
import core.basesyntax.transaction.FruitTransaction;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        // 1. Read the data from the input CSV file
        String filePath = args.length > 0
                        ? args[0]
                        : System.getProperty("config.file", "data/data.csv");
        // 2. Convert the incoming data into FruitTransactions list
        LineReader lr = LineReaderFactory.create(ReaderType.CSV);
        DataConverter converter = new DataConverterImpl();
        try (Stream<String> lines = lr.readLines(filePath)) {
            Stream<FruitTransaction> transactions =
                    converter.convertToTransaction(lines);
            transactions.forEach(tx -> {
                tx.execute();
            });
        }
        // 3. Create and feel the map with all OperationHandler implementations
        // 4. Process the incoming transactions with applicable OperationHandler implementations
        // 5.Generate report based on the current Storage state
        // 6. Write the received report into the destination file
        writeReport();
    }

    private static void writeReport() throws IOException {
        ReportGenerator generator = new ReportGeneratorImpl();
        ReportWriter rw = new CsvWriter();
        rw.write("finalReport.csv",
                generator.getReport());
    }
}
