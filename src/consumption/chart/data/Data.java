package consumption.chart.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Data {
    private String type;
    private BigDecimal price;
    private BigDecimal amount;
    private LocalDate date;

    private Data(Builder builder) {
        type = builder.type;
        price = builder.price;
        amount = builder.amount;
        date = builder.date;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String type;
        private BigDecimal price;
        private BigDecimal amount;
        private LocalDate date;

        private Builder() {
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Data build() {
            return new Data(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(type, data.type) &&
                Objects.equals(price, data.price) &&
                Objects.equals(amount, data.amount) &&
                Objects.equals(date, data.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, price, amount, date);
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
//                ", price=" + price +
//                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
