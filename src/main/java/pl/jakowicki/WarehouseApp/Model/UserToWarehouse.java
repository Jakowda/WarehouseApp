package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "users_to_warehouses")
public class UserToWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "warehouse_id")
    private Long warehouseId;

    public UserToWarehouse() {
    }

    public UserToWarehouse(Long userId, Long warehouseId) {
        this.userId = userId;
        this.warehouseId = warehouseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
