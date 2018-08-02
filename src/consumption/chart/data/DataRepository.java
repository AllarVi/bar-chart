package consumption.chart.data;

import java.util.List;
import java.util.function.Predicate;

public interface DataRepository {

    List<Data> getData(Predicate<Data> filterPredicate);
}
