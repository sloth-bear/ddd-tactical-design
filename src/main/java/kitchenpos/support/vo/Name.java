package kitchenpos.support.vo;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.persistence.Embeddable;
import org.apache.logging.log4j.util.Strings;

@Embeddable
public class Name {

    private String value;

    protected Name() {
    }

    private Name(final String value) {
        this.value = value;
    }

    public static Name create(final String value) {
        checkArgument(Strings.isNotEmpty(value), "name must not be empty. value: %s", value);

        return new Name(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name = (Name) o;
        return Objects.equal(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("value", value)
            .toString();
    }

    public String getValue() {
        return value;
    }
}