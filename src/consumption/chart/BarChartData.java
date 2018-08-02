package consumption.chart;

import java.util.Objects;

public class BarChartData {

    private String xValue;
    private Number yValue;

    private BarChartData(Builder builder) {
        xValue = builder.xValue;
        yValue = builder.yValue;
    }

    String getXValue() {
        return xValue;
    }

    Number getYValue() {
        return yValue;
    }

    static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String xValue;
        private Number yValue;

        private Builder() {
        }

        Builder xValue(String xValue) {
            this.xValue = xValue;
            return this;
        }

        Builder yValue(Number yValue) {
            this.yValue = yValue;
            return this;
        }

        BarChartData build() {
            return new BarChartData(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarChartData that = (BarChartData) o;
        return Objects.equals(xValue, that.xValue) &&
                Objects.equals(yValue, that.yValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xValue, yValue);
    }
}
