package consumption.observer;

import javafx.scene.chart.BarChart;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class Grid extends GridPane {

    private static Grid instance = null;

    private Grid() {
        // Exists only to defeat instantiation.
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }
}
