package core.basesyntax.inout;

import java.io.IOException;
import java.util.stream.Stream;

public interface ReportWriter {
    void write(String path, Stream<String[]> rows) throws IOException;
}