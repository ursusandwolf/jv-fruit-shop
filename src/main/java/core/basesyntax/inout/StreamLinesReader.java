package core.basesyntax.inout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class StreamLinesReader implements LineReader {

    @Override
    public Stream<String> readLines(String path) throws IOException {
        return Files.lines(Path.of(path));
    }
}
