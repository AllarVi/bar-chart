package consumption.typefilter;

import consumption.chart.data.DataRepositoryImpl;
import javafx.scene.control.ComboBox;

import java.util.List;

public class TypeFilterController {

    private final TypeFilterService typeFilterService;

    public TypeFilterController() {
        typeFilterService = new TypeFilterService(new DataRepositoryImpl());
    }

    public ComboBox<String> getTypeFilter() {
        final ComboBox<String> typeFilter = new ComboBox<>();

        List<String> types = typeFilterService.getTypes();
        typeFilter.getItems().addAll(types);

        typeFilter.getSelectionModel().selectFirst();

        return typeFilter;
    }
}
