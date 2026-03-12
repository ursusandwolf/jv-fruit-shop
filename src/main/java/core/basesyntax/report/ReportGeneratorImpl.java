package core.basesyntax.report;

import core.basesyntax.db.Storage;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport(Storage storage) {
        Integer banana = storage.read("banana");
        return "banana: " + banana;
    }
}
