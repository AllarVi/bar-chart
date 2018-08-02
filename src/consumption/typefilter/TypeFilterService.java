package consumption.typefilter;

import consumption.chart.data.Data;
import consumption.chart.data.DataRepository;

import java.util.List;
import java.util.stream.Collectors;

import static consumption.Constants.TYPE_ALL;

class TypeFilterService {


    private DataRepository dataRepository;

    TypeFilterService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    List<String> getTypes() {
        List<Data> data = dataRepository.getData(x -> true);
        List<String> collect = data.stream()
                .map(Data::getType)
                .distinct()
                .collect(Collectors.toList());
        collect.add(0, TYPE_ALL);
        return collect;
    }
}
