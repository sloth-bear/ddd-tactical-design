package kitchenpos.menus.tobe.domain.menu;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuProductQuantity {
    @Column(name = "quantity", nullable = false)
    private Long menuProductQuantity;

    protected MenuProductQuantity() {
    }

    public MenuProductQuantity(Long menuProductQuantity) {
        validateMenuProductQuantity(menuProductQuantity);
        this.menuProductQuantity = menuProductQuantity;
    }

    private void validateMenuProductQuantity(Long menuProductQuantity) {
        if (menuProductQuantity < 0) {
            throw new IllegalArgumentException();
        }
    }


    public Long getMenuProductQuantity() {
        return menuProductQuantity;
    }
}