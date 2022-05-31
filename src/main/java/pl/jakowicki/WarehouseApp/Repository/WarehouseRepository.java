package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    @Query(value="SELECT * FROM `warehouse` WHERE warehouse_id = ?1", nativeQuery = true)
    Warehouse findWarehouseById(Long warehouseId);
}

