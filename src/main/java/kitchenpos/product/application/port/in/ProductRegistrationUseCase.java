package kitchenpos.product.application.port.in;

import kitchenpos.product.application.exception.ContainsProfanityException;
import kitchenpos.product.domain.ProductPrice;
import kitchenpos.support.vo.Name;

public interface ProductRegistrationUseCase {

    /**
     * @throws ContainsProfanityException nameCandidate에 비속어가 포함되어 있을 때
     */
    ProductDTO register(final Name productNameCandidate, final ProductPrice price);
}
