package consumption.chart.data;

public class AmountValidationRule implements DataRule {
    @Override
    public String validate(String data) {
        data = data.replaceAll(",", ".");

        Double number = Double.parseDouble(data);
        if (number < 0) {
            throw new IllegalArgumentException("Data amount can't contain negative values!");
        }

        return data;
    }
}
