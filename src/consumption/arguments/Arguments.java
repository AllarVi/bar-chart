package consumption.arguments;

import java.util.HashMap;

public class Arguments extends HashMap<String, String> {

    public static final String INPUT_FILE = "inputFile";

    private static Arguments instance = null;

    private Arguments() {
        // Exists only to defeat instantiation.
    }

    public static Arguments getInstance() {
        if (instance == null) {
            instance = new Arguments();
        }
        return instance;
    }
}
