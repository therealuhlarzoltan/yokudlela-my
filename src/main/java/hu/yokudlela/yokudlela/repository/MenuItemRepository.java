package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
