package consumption.chart;


import consumption.chart.data.DataRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class BarChartController {

    private final BarChartService barChartService;

    public BarChartController() {
        barChartService = new BarChartService(new DataRepositoryImpl());
    }

    public BarChart<String, Number> getBarChart(String type) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);

        chart.setTitle("Refuelling");
        xAxis.setLabel("Month");
        yAxis.setLabel("Amount");
        yAxis.setUpperBound(200);

        ObservableList<XYChart.Series<String, Number>> chartData = this.getBarChartData(type);

        chart.getData().addAll(chartData);

        chart.setMinWidth(800);

        return chart;
    }

    private ObservableList<XYChart.Series<String, Number>> getBarChartData(String type) {
        List<XYChart.Data<String, Number>> barChartData = barChartService.getBarChartData(type);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        barChartData.forEach(chartData -> series.getData().add(chartData));

        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        data.add(series);
        return data;
    }

}
