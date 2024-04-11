package kitchenpos.menu.infra;

import java.util.List;
import java.util.UUID;
import kitchenpos.menu.tobe.domain.Menu;
import kitchenpos.menu.tobe.domain.MenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaMenuRepository extends MenuRepository, JpaRepository<Menu, UUID> {

    @Query("select m from Menu m join m.menuProducts.value mp where mp = :productId")
    @Override
    List<Menu> findAllByProductId(@Param("productId") UUID productId);
}