package core.basesyntax.inout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReadAllLinesReader implements LineReader {

    @Override
    public Stream<String> readLines(String path) throws IOException {
        return Files.readAllLines(Path.of(path)).stream();
    }
}
