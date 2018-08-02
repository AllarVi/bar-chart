package consumption.chart.data;

import consumption.arguments.Arguments;
import consumption.observer.FileObservable;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static consumption.arguments.Arguments.INPUT_FILE;

public class DataRepositoryImpl implements DataRepository {

    private static final String PIPE = "\\|";

    public List<Data> getData(Predicate<Data> filterPredicate) {
        List<Data> data = new ArrayList<>();
        Arguments arguments = Arguments.getInstance();
        try {
            File file = new File(arguments.get(INPUT_FILE));

            FileObservable fileObservable = FileObservable.getInstance();
            if (fileObservable.getFile() == null) {
                fileObservable.setFile(file);
            }

            InputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data = reader.lines()
                    .map(mapToItem)
                    .filter(Objects::nonNull)
                    .filter(filterPredicate)
                    .collect(Collectors.toList());

            reader.close();
        } catch (IOException e) {
            System.err.println("ERROR: Error reading input file!");
        }

        System.out.println("LOG: data = " +
                data);
        return data;
    }

    private Function<String, Data> mapToItem = (line) -> {
        String[] p = line.split(PIPE);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        LocalDate date;

        try {
            p[1] = new AmountValidationRule().validate(p[1]);
            p[2] = new AmountValidationRule().validate(p[2]);
            date = format.parse(p[3]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | ParseException exception) {
            System.err.println("WARNING: Encountered invalid input data! Will skip.");
            return null;
        }

        return Data.newBuilder()
                .type(p[0])
                .price(new BigDecimal(p[1]))
                .amount(new BigDecimal(p[2]))
                .date(date)
                .build();
    };
}
