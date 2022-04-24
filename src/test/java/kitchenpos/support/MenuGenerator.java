package kitchenpos.support;

import kitchenpos.menus.domain.tobe.MenuProduct;
import kitchenpos.menus.domain.tobe.menugroup.MenuGroup;

import java.util.UUID;

public class MenuGenerator {

    public static MenuProduct createMenuProduct(long seq, long quantity) {
        return new MenuProduct(seq, quantity, UUID.randomUUID());
    }

    public static MenuGroup createMenuGroup(String menuGroupName) {
        return new MenuGroup(menuGroupName, new StubBanWordFilter(false));
    }
}