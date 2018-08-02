package consumption.observer;

import consumption.chart.BarChartController;

import java.io.File;

public class FileObservable implements Runnable {

    private Long lastModified;
    private File file;

    private BarChartController barChartController;

    public void setBarChartController(BarChartController barChartController) {
        this.barChartController = barChartController;
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
        // Uncomment to run task
//        while (true) {
//            if (this.file != null) {
//                System.out.println(file.lastModified());
//                if (this.lastModified != null && file.lastModified() != this.lastModified) {
//                    System.out.println("Reached");
//                    Grid.getInstance().getChildren().remove(BarChartQueue.getInstance().poll());
//                    BarChart<String, Number> newBarChart = this.barChartController.getBarChart(TYPE_ALL);
//                    Grid.getInstance().add(newBarChart, 1, 1);
//                    BarChartQueue.getInstance().add(newBarChart);
//                }
//                this.lastModified = file.lastModified();
//
//            } else {
//                System.out.println("File not set!");
//            }
//        }
    }
}
