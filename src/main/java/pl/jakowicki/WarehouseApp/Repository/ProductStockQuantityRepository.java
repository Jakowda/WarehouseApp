package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.ProductStockQuantity;


import java.util.List;

@Repository
public interface ProductStockQuantityRepository extends JpaRepository<ProductStockQuantity, Long> {

    @Query(value ="SELECT * FROM productstockquantity WHERE id_warehouse = :warehouseID", nativeQuery = true )
    public List<ProductStockQuantity> findAllSockQuantityFromWarehouse(Long warehouseID);

    @Query(value ="SELECT * FROM productstockquantity WHERE id_product = :productID", nativeQuery = true )
    List<ProductStockQuantity> findAllSockQuantityByProduct(Long productID);

    @Query(value ="SELECT * FROM productstockquantity WHERE id_product = :productID AND id_warehouse = :warehouseID", nativeQuery = true )
    ProductStockQuantity findAllStockQuantityByProductAndWarehouse(Long productID, Long warehouseID);

    @Query(value ="SELECT product_stock_quantity_id FROM productstockquantity WHERE id_product = :productID AND id_warehouse = :warehouseID", nativeQuery = true )
    Long findIdByProductAndWarehouse(Long productID, Long warehouseID);
}
