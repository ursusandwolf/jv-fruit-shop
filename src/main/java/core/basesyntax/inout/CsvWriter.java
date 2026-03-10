package core.basesyntax.inout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvWriter implements ReportWriter {

    @Override
    public void write(String path, String content) throws IOException {
        Files.writeString(Path.of(path), content);
    }
}