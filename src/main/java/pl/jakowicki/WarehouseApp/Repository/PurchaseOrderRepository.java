package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.PurchaseOrder;
import pl.jakowicki.WarehouseApp.Model.PurchaseOrderLine;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    PurchaseOrder findAllByPurchaseOrderName(String purchaseOrderName);

    PurchaseOrder findAllById(Long id);

//    List<PurchaseOrder> findAllPurchaseOrderByWarehouseId(Long warehouseId);

    @Query(value = "SELECT * FROM `purchaseorder` WHERE id_warehouse = ?1",
            nativeQuery = true)
    public  List<PurchaseOrder> findAllPurchaseOrderByWarehouseId(Long warehouseId);
}
