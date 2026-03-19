package core.basesyntax;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.db.Storage;
import core.basesyntax.inout.CsvWriter;
import core.basesyntax.inout.LineReader;
import core.basesyntax.inout.LineReaderFactory;
import core.basesyntax.inout.ReaderType;
import core.basesyntax.inout.ReportWriter;
import core.basesyntax.parser.DataConverter;
import core.basesyntax.parser.DataConverterImpl;
import core.basesyntax.report.ReportGenerator;
import core.basesyntax.report.ReportGeneratorImpl;
import core.basesyntax.transaction.ShopTransaction;
import core.basesyntax.transaction.Strategy;
import core.basesyntax.transaction.Transaction;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {

    public static final Storage SHOP_STORAGE = new ShopStorage();

    public static void main(String[] args) throws IOException {
        // 1. Read the data from the input CSV file
        String filePath = args.length > 0
                ? args[0]
                : System.getProperty("config.file", "data/data.csv");
        // 2. Convert the incoming data into FruitTransactions list
        LineReader reader = LineReaderFactory.create(ReaderType.CSV);
        DataConverter converter = new DataConverterImpl();
        Transaction shopTransaction = new ShopTransaction(SHOP_STORAGE);

        try (Stream<String> lines = reader.readLines(filePath)) {
            converter.convertToTransaction(lines)
                    .forEach(ft -> {
                        Strategy.execute(ft, shopTransaction);
                    });
        }
        writeReport();
    }

    private static void writeReport() throws IOException {
        ReportWriter writer = new CsvWriter();
        ReportGenerator generator = new ReportGeneratorImpl();
        writer.write("finalReport.csv",
                generator.getReport(SHOP_STORAGE));
    }
}
