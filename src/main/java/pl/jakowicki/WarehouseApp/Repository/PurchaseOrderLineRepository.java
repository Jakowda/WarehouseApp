package pl.jakowicki.WarehouseApp.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.PurchaseOrderLine;

import java.util.List;

@Repository
public interface PurchaseOrderLineRepository extends CrudRepository<PurchaseOrderLine, Long> {

    @Query(value = "SELECT * FROM cwiczenie WHERE treningID = ?1",
            nativeQuery = true)
    public void znajdzCwiczeniaZTreningu(PurchaseOrderLine purchaseOrderLine);

//    @Query(value = "INSERT INTO purchaseorderline (id_product, order_amount, purchase_price, id_purchase_order)" +
//            "VALUES [product_id, order_amount, purchase_price, id_purchase_order]", nativeQuery = true)
//    public void insertPurchaseOrderLine(@Param("product_id")Long product_id, @Param("order_amount") int order_amount, @Param("purchase_price") double purchase_price, @Param("id_purchase_order") Long id_purchase_order);

}

