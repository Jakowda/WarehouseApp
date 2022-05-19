package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.SalesOrder;

import java.util.List;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {

    SalesOrder findSalesOrderById(Long id);

    @Query(value = "SELECT * FROM `salesorder` WHERE id_warehouse = ?1",
            nativeQuery = true)
    List<SalesOrder> findAllSalesOrderByWarehouseId(Long warehouseId);
}
