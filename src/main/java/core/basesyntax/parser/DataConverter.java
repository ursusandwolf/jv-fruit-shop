package core.basesyntax.parser;

import java.util.List;

public interface DataConverter {
    List<?> convertToTransaction (Iterable<String> strings);
}
