package consumption.typefilter;

public class TypeFilterValue {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static TypeFilterValue instance = null;

    private TypeFilterValue() {
        // Exists only to defeat instantiation.
    }

    public static TypeFilterValue getInstance() {
        if (instance == null) {
            instance = new TypeFilterValue();
        }
        return instance;
    }
}
