package consumption.observer;

import javafx.scene.chart.BarChart;

import java.util.LinkedList;

public class BarChartQueue extends LinkedList<BarChart<String, Number>> {

    private static BarChartQueue instance = null;

    private BarChartQueue() {
        // Exists only to defeat instantiation.
    }

    public static BarChartQueue getInstance() {
        if (instance == null) {
            instance = new BarChartQueue();
        }
        return instance;
    }
}
