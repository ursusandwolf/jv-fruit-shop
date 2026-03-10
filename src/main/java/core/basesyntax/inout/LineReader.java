package core.basesyntax.inout;

import java.io.IOException;
import java.util.stream.Stream;

public interface LineReader {
    Stream<String> readLines(String path) throws IOException;
}
