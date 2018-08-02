package consumption.chart;

import consumption.arguments.Arguments;
import consumption.chart.data.Data;
import consumption.chart.data.DataRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static consumption.arguments.Arguments.INPUT_FILE;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataRepositoryImplTest {

    private final DataRepositoryImpl dataRepositoryImpl;

    DataRepositoryImplTest() {
        dataRepositoryImpl = new DataRepositoryImpl();
    }

    @Test
    void shouldReadChartData() {
        String inputFilePath = Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource("simpleChartData.csv"))
                .getPath();

        Arguments arguments = Arguments.getInstance();
        arguments.put(INPUT_FILE, inputFilePath);

        Predicate<Data> filterPredicate = chartData -> "98".equals(chartData.getType());

        List<Data> data = dataRepositoryImpl.getData(filterPredicate);

        Data data1 = Data.newBuilder()
                .type("98")
                .price(new BigDecimal("1.319"))
                .amount(new BigDecimal("50.56"))
                .date(LocalDate.of(2016, 1, 1))
                .build();

        Data data2 = Data.newBuilder()
                .type("98")
                .price(new BigDecimal("1.319"))
                .amount(new BigDecimal("45.32"))
                .date(LocalDate.of(2016, 1, 15))
                .build();

        List<Data> expectedData = asList(data1, data2);

        assertEquals(2, data.size());
        IntStream.range(0, data.size())
                .forEach(idx -> {
                    assertEquals(expectedData.get(idx).getType(), data.get(idx).getType());
                    assertEquals(expectedData.get(idx).getAmount(), data.get(idx).getAmount());
                    assertEquals(expectedData.get(idx).getPrice(), data.get(idx).getPrice());
                    assertEquals(expectedData.get(idx).getDate(), data.get(idx).getDate());
                });
    }
}
