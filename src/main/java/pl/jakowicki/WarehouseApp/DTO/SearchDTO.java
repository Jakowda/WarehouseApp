package pl.jakowicki.WarehouseApp.DTO;

import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Model.Warehouse;

public class SearchDTO {
    private Warehouse warehouse;
    private Product product;

    public SearchDTO() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
