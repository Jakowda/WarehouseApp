package pl.jakowicki.WarehouseApp.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.OrderDeliveryLine;

@Repository
public interface DeliveryLineRepository extends CrudRepository<OrderDeliveryLine, Long> {
}
