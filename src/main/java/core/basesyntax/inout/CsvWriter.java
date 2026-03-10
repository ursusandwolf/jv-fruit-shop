package core.basesyntax.inout;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CsvWriter implements ReportWriter {

    @Override
    public void write(String path, Stream<String[]> rows) throws IOException {

        try (Writer writer = Files.newBufferedWriter(Path.of(path));
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            rows.forEach(row -> {
                try {
                    printer.printRecord((Object[]) row);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}