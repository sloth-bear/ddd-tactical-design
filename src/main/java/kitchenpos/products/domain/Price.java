package kitchenpos.products.domain;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {
    private static final BigDecimal MIN_PRICE = BigDecimal.ZERO;
    private static final int LESS_CONDITION = 0;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    protected Price() {

    }

    public Price(BigDecimal price) {
        validate(price);
        this.price = price;
    }

    public static Price from(long price) {
        return new Price(BigDecimal.valueOf(price));
    }

    private void validate(BigDecimal price) {
        if (Objects.isNull(price)) {
            throw new IllegalArgumentException("가격은 비어 있을수 없습니다.");
        }

        if (isLess(price)) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 한다");
        }
    }

    private boolean isLess(BigDecimal price) {
        return MIN_PRICE.compareTo(price) > LESS_CONDITION;
    }


    public BigDecimal value() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}