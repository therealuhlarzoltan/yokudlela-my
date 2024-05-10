package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findByPriceLessThanEqual(double value);
}
