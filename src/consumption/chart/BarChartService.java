package consumption.chart;

import consumption.chart.data.Data;
import consumption.chart.data.DataRepository;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

class BarChartService {

    private DataRepository dataRepository;

    BarChartService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    List<XYChart.Data<String, Number>> getBarChartData(String type) {
        Predicate<Data> filterPredicate = FilterPredicateFactory.getFilterPredicate(type);

        List<Data> chartData = dataRepository.getData(filterPredicate);

        Map<Integer, List<Data>> groupedByDate = chartData.stream()
                .collect(groupingBy(data -> data.getDate().getMonthValue()));

        List<BigDecimal> monthlyValues = groupedByDate.entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .map(d -> d.getAmount().multiply(d.getPrice()))
                        .reduce(ZERO, (x, y) -> x.add(y).setScale(3, ROUND_HALF_UP)))
                .collect(toList());

        Optional<BigDecimal> maxValue = monthlyValues.stream().reduce(BigDecimal::max);
        Optional<BigDecimal> minValue = monthlyValues.stream().reduce(BigDecimal::min);

        List<XYChart.Data<String, Number>> barChartData = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            List<Data> data = groupedByDate.get(i);
            String xValue = Month.of(i).toString();
            BigDecimal yValue;

            if (data != null) {
                yValue = data.stream()
                        .map(d -> d.getAmount().multiply(d.getPrice()))
                        .reduce(ZERO, (x, y) -> x.add(y).setScale(3, ROUND_HALF_UP));
            } else {
                yValue = ZERO;
            }

            XYChart.Data<String, Number> XYChartData = new XYChart.Data<>(xValue, yValue);
            XYChartData.nodeProperty().addListener((ov, oldNode, newNode) -> {
                if (newNode != null) {
                    Node node = XYChartData.getNode();
                    if (maxValue.isPresent() && yValue.equals(maxValue.get())) {
                        node.setStyle("-fx-bar-fill: red;");
                    } else if (minValue.isPresent() && yValue.equals(minValue.get())) {
                        node.setStyle("-fx-bar-fill: green;");
                    } else {
                        node.setStyle("-fx-bar-fill: yellow;");
                    }
                    this.displayLabelForData(XYChartData);
                }
            });
            barChartData.add(XYChartData);
        }

        return barChartData;
    }

    private void displayLabelForData(XYChart.Data<String, Number> data) {
        final Node node = data.getNode();
        final Text dataText = new Text(String.format("%.3f", data.getYValue().doubleValue()) + "");
        node.parentProperty().addListener((ov, oldParent, parent) -> {
            Group parentGroup = (Group) parent;
            parentGroup.getChildren().add(dataText);
        });

        node.boundsInParentProperty().addListener((ov, oldBounds, bounds) -> {
            dataText.setLayoutX(
                    Math.round(
                            bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                    )
            );
            dataText.setLayoutY(
                    Math.round(
                            bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                    )
            );
        });
    }
}
