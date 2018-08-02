package consumption.chart;

import consumption.chart.data.Data;

import java.util.function.Predicate;

import static consumption.Constants.TYPE_ALL;

class FilterPredicateFactory {
    static Predicate<Data> getFilterPredicate(String type) {
        Predicate<Data> filterPredicate;
        switch (type) {
            case TYPE_ALL:
                filterPredicate = chartData -> true;
                break;
            default:
                filterPredicate = chartData -> type.equals(chartData.getType());
        }

        return filterPredicate;
    }
}
