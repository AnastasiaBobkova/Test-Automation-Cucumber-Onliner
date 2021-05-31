package onliner.pages;

public enum Filters {
    PRODUCER("Производитель"),
    MAXPRICE("до"),
    RESOLUTION("Разрешение"),
    MINDIAGONAL("Диагональ"),
    MAXDIAGONAL("Диагональ");

    private String value;

    /**
     * Constructor
     * @param valueToSet value
     */

    Filters(final String valueToSet) {
        value = valueToSet;
    }

    /**
     * Returns string value
     * @return String value
     */
    public String toString() {
        return value;
    }
}
