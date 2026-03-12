package core.basesyntax.report;

import core.basesyntax.db.Storage;

public interface ReportGenerator {
    String getReport(Storage storage);
}
