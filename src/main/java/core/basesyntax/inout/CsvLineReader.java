package core.basesyntax.inout;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CsvLineReader implements LineReader {

    @Override
    public Stream<String> readLines(String path) throws IOException {

        Reader reader = Files.newBufferedReader(Path.of(path));

        Iterable<CSVRecord> records =
                CSVFormat.DEFAULT.parse(reader);

        return StreamSupport.stream(records.spliterator(), false)
                .map(record -> String.join(",", record));
    }
}