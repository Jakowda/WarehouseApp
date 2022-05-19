package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.SalesOrderLine;

@Repository
public interface SalesOrderLineRepository extends CrudRepository<SalesOrderLine, Long> {
}
