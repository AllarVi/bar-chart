package consumption.chart;

import consumption.chart.data.Data;
import consumption.chart.data.DataRepository;

import java.util.List;
import java.util.function.Predicate;

public class DummyDataRepository implements DataRepository {

    private List<Data> data;

    public DummyDataRepository(List<Data> data) {
        this.data = data;
    }

    @Override
    public List<Data> getData(Predicate<Data> filterPredicate) {
        return this.data;
    }
}
