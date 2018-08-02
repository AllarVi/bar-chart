package consumption.typefilter;

import consumption.chart.data.Data;
import consumption.chart.DummyDataRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static consumption.Constants.TYPE_ALL;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeFilterServiceTest {

    private final TypeFilterService typeFilterService;

    TypeFilterServiceTest() {
        List<Data> data = asList(
                Data.newBuilder().type("type1").build(),
                Data.newBuilder().type("type2").build(),
                Data.newBuilder().type("type2").build(),
                Data.newBuilder().type("type3").build()
        );
        typeFilterService = new TypeFilterService(new DummyDataRepository(data));
    }

    @Test
    void shouldGetDistinctTypes() {
        assertEquals(asList(TYPE_ALL, "type1", "type2", "type3"), typeFilterService.getTypes());
    }
}
