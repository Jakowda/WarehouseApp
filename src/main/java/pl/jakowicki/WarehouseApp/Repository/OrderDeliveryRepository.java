package pl.jakowicki.WarehouseApp.Repository;

import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.OrderDelivery;

import java.util.List;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

   OrderDelivery findAllByDeliveryName(String deliveryName);

   OrderDelivery findAllById(Long id);

    @Query(value = "SELECT * FROM `orderdelivery` WHERE id_warehouse = ?1",
            nativeQuery = true)
    List<OrderDelivery> getOrderDeliveryByWarehouseId(Long warehouseId);
}
