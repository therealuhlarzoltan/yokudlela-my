package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    boolean existsByOrderGroup(String orderGroup);
    List<Order> findByOrderGroup(String orderGroup);
}
