package hu.yokudlela.yokudlela.repository;

import hu.yokudlela.yokudlela.domain.entity.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends CrudRepository<Table, Long> {
    public Table findByName(@Param(value = "name") String name);

    public List<Table> findByCapacityGreaterThanEqual(@Param(value = "capacity") Byte capacity);


    public List<Table> findByIsAvailable(boolean pAvailable);
}
