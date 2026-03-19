package core.basesyntax.report;

import core.basesyntax.db.Storage;

public class ReportGeneratorImpl implements ReportGenerator {

    public static final String FRUIT_QUANTITY = "fruit,quantity";
    public static final String LINED = System.lineSeparator();

    @Override
    public String getReport(Storage storage) {
        StringBuilder sb = new StringBuilder(FRUIT_QUANTITY).append(LINED);

        storage.getAllKeys()
                .forEach(k -> sb.append(k).append(",")
                        .append(storage.read(k)).append(LINED));
        return sb.toString();
    }
}
