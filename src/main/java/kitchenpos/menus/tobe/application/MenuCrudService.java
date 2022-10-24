package kitchenpos.menus.tobe.application;

import kitchenpos.menus.tobe.domain.menu.*;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroupRepository;
import kitchenpos.menus.tobe.dto.menu.CreateMenuRequest;
import kitchenpos.menus.tobe.dto.menu.MenuProductRequest;
import kitchenpos.products.infra.PurgomalumClient;
import kitchenpos.products.tobe.domain.Name;
import kitchenpos.products.tobe.domain.Price;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class MenuCrudService {

    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public MenuCrudService(final MenuRepository menuRepository, final ProductRepository productRepository, final MenuGroupRepository menuGroupRepository, final PurgomalumClient purgomalumClient) {
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public Menu create(final CreateMenuRequest request) {
        MenuGroup menuGroup = menuGroupRepository.findById(request.getMenuGroupId()).orElseThrow(() -> new NoSuchElementException("해당하는 메뉴 그룹이 업습니다."));
        validateExistProduct(request.getMenuProducts());
        boolean isProfanity = !Objects.isNull(request.getMenuName()) && purgomalumClient.containsProfanity(request.getMenuName());
        return new Menu(new Name(request.getMenuName(), isProfanity), new Price(request.getPrice()), createMenuProducts(request), menuGroup);
    }

    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    private void validateExistProduct(List<MenuProductRequest> menuProducts) {
        for (MenuProductRequest menuProductRequest : menuProducts) {
            productRepository.findById(menuProductRequest.getProductRequest().getProductId()).orElseThrow(() -> new NoSuchElementException("해당하는 상품이 업습니다."));
        }
    }

    private MenuProducts createMenuProducts(CreateMenuRequest request) {
        MenuProducts menuProducts = new MenuProducts();
        request.getMenuProducts().stream().map(MenuCrudService::createMenuProduct).forEach(menuProducts::add);
        return menuProducts;
    }

    private static MenuProduct createMenuProduct(MenuProductRequest menuProductRequest) {
        return new MenuProduct(createProduct(menuProductRequest), new Quantity(menuProductRequest.getQuantity()));
    }

    private static Product createProduct(MenuProductRequest menuProductRequest) {
        return new Product(UUID.randomUUID(), new Name(menuProductRequest.getProductRequest().getProductName(), false),
                new Price(menuProductRequest.getProductRequest().getProductPrice()));
    }

}