package consumption.chart;

import consumption.chart.data.Data;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BarChartServiceTest {

    private final BarChartService barChartService;

    BarChartServiceTest() {
        List<Data> data = asList(
                Data.newBuilder()
                        .type("98")
                        .price(new BigDecimal("1.319"))
                        .amount(new BigDecimal("50.56"))
                        .date(LocalDate.of(2016, 3, 1))
                        .build(),
                Data.newBuilder()
                        .type("98")
                        .price(new BigDecimal("1.319"))
                        .amount(new BigDecimal("45.32"))
                        .date(LocalDate.of(2016, 3, 15))
                        .build(),
                Data.newBuilder()
                        .type("D")
                        .price(new BigDecimal("1.219"))
                        .amount(new BigDecimal("48.32"))
                        .date(LocalDate.of(2016, 12, 7))
                        .build()
        );
        barChartService = new BarChartService(new DummyDataRepository(data));
    }

    @Test
    void shouldGetBarChartData() {
        BigDecimal yValue = new BigDecimal("126.466");
        XYChart.Data<String, Number> XYChartData = new XYChart.Data<>("MARCH", yValue);

        List<XYChart.Data<String, Number>> barChartData = barChartService.getBarChartData("98");

        assertEquals(12, barChartData.size());
        assertEquals(XYChartData.getXValue(), barChartData.get(2).getXValue());
        assertEquals(XYChartData.getYValue(), barChartData.get(2).getYValue());
    }
}
