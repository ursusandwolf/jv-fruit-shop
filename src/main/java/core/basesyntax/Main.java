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
import java.util.List;

//TODO: Використовувати Reader/Writer інтерфейси замість прямого Files/FileWriter;
// закривати ресурси через try-with-resources.
//TODO: Не кастити List<?> — змінити сигнатуру DataConverter на List<FruitTransaction>.

public class Main {

    public static void main(String[] args) throws IOException {
        // 1. Read the data from the input CSV file
        String filePath = args.length > 0
                        ? args[0]
                        : System.getProperty("input.file", "data.csv");
        LineReader lr = LineReaderFactory.create(ReaderType.CSV);

        List<String> lines = lr.readLines(filePath).toList();
        // 2. Convert the incoming data into FruitTransactions list
        DataConverter converter = new DataConverterImpl();
        List<FruitTransaction> transactions
                = (List<FruitTransaction>) converter.convertToTransaction(lines);
        // 3. Create and feel the map with all OperationHandler implementations
        // 4. Process the incoming transactions with applicable OperationHandler implementations
        for (FruitTransaction tx : transactions) {
            //tx.execute();
        }
        // 5.Generate report based on the current Storage state
        ReportGenerator generator = new ReportGeneratorImpl();
        String report = generator.getReport();
        // 6. Write the received report into the destination file
        ReportWriter rw = new CsvWriter();
        rw.write("finalReport.csv", report);
    }
}
