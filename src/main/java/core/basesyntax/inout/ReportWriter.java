package core.basesyntax.inout;

import java.io.IOException;

public interface ReportWriter {
    void write(String path, String content) throws IOException;
}
