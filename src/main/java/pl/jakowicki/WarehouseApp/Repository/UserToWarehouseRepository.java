package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.jakowicki.WarehouseApp.Model.UserToWarehouse;

public interface UserToWarehouseRepository extends CrudRepository<UserToWarehouse, Long> {
    @Query(value = "SELECT * FROM users_to_warehouses WHERE warehouse_id = ?1 AND user_id = ?2",
            nativeQuery = true)
    UserToWarehouse findAllByWarehouseAndUserID(Long warehouseId, Long userId);

//    @Query(value = "DELETE FROM users_to_warehouses WHERE warehouse_id = ?1 AND user_id = ?2",
//            nativeQuery = true)
//    void deleteConnection(userToWarehouse);
}
