package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
}
