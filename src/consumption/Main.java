package consumption;

import consumption.arguments.Arguments;
import consumption.chart.BarChartController;
import consumption.observer.BarChartQueue;
import consumption.observer.FileObservable;
import consumption.observer.Grid;
import consumption.typefilter.TypeFilterController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static consumption.Constants.APP_TITLE;
import static consumption.arguments.Arguments.INPUT_FILE;

public class Main extends Application {

    private final BarChartController barChartController;
    private final TypeFilterController typeFilterController;

    public Main() {
        barChartController = new BarChartController();
        typeFilterController = new TypeFilterController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(APP_TITLE);
        Scene scene = new Scene(new Group());

        GridPane grid = Grid.getInstance();
        initFileObserver();

        Insets insets = new Insets(10, 10, 10, 10);
        grid.setPadding(insets);
        grid.setVgap(20);
        grid.setHgap(10);

        ComboBox<String> typeFilter = typeFilterController.getTypeFilter();
        BarChart<String, Number> barChart = barChartController.getBarChart(typeFilter.valueProperty().getValue());
        BarChartQueue.getInstance().add(barChart);

        typeFilter.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue.equals(oldValue)) {
                        return;
                    }

                    grid.getChildren().remove(BarChartQueue.getInstance().poll());
                    BarChart<String, Number> newBarChart = barChartController.getBarChart(newValue);
                    grid.add(newBarChart, 1, 1);
                    BarChartQueue.getInstance().add(newBarChart);
                }
        );

        grid.add(new Label("Fuel type: "), 0, 0);
        grid.add(typeFilter, 1, 0);
        grid.add(barChart, 1, 1);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initFileObserver() {
        List<Thread> threads = new ArrayList<>();

        Runnable observeFileTask = FileObservable.getInstance();
        ((FileObservable) observeFileTask).setBarChartController(this.barChartController);

        Thread worker = new Thread(observeFileTask);

        worker.setName("fileObservable");
        worker.start();

        threads.add(worker);
    }


    public static void main(String[] args) {
        Arguments arguments = Arguments.getInstance();

        if (args.length > 0) {
            arguments.put(INPUT_FILE, args[0]);
        } else {
            System.err.println("Please supply input file as an argument");
            System.exit(1);
        }

        launch(args);
    }
}
