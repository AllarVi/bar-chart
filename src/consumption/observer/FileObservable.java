package consumption.observer;

import consumption.chart.BarChartController;
import consumption.typefilter.TypeFilterValue;
import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.GridPane;

import java.io.File;

public class FileObservable implements Runnable {

    private Long lastModified;
    private File file;
    private GridPane grid;
    private BarChartController barChartController;

    public void setBarChartController(BarChartController barChartController) {
        this.barChartController = barChartController;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private static FileObservable instance = null;

    private FileObservable() {
        // Exists only to defeat instantiation.
    }

    public static FileObservable getInstance() {
        if (instance == null) {
            instance = new FileObservable();
        }
        return instance;
    }

    @Override
    public void run() {
        while (true) {
            if (this.file != null) {
                if (this.lastModified != null && file.lastModified() != this.lastModified) {
                    System.out.println("Updating chart");
                    Platform.runLater(() -> {
                        this.grid.getChildren().remove(BarChartQueue.getInstance().poll());
                        BarChart<String, Number> newBarChart = this.barChartController.getBarChart(TypeFilterValue.getInstance().getValue());
                        this.grid.add(newBarChart, 1, 1);
                        BarChartQueue.getInstance().add(newBarChart);
                    });
                }
                this.lastModified = file.lastModified();
            } else {
                System.out.println("File not set!");
            }
        }
    }
}
