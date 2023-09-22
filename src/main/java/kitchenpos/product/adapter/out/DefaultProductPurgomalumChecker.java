package kitchenpos.product.adapter.out;

import static kitchenpos.support.ParameterValidateUtils.checkNotNull;

import java.util.List;
import kitchenpos.product.application.port.out.ProductPurgomalumChecker;
import kitchenpos.support.vo.Name;

public class DefaultProductPurgomalumChecker implements ProductPurgomalumChecker {

    private static final List<String> PROFANITIES = List.of("바보", "멍청이", "말미잘");

    @Override
    public boolean containsProfanity(final Name name) {
        checkNotNull(name, "name");

        // API 호출 대신 임시로 구현
        return PROFANITIES.contains(name.getValue());
    }
}
