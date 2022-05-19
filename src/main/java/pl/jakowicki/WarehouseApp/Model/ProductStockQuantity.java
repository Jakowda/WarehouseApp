package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "productstockquantity")
public class ProductStockQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_stock_quantity_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private int quantity;

    ProductStockQuantity(){}

    public ProductStockQuantity(Warehouse warehouse, Product product, int quantity) {
        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getProductStockQuantity_id() {
        return id;
    }

    public void setProductStockQuantity_id(Long productStockQuantity_id) {
        this.id = productStockQuantity_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductStockQuantity{" +
                "productStockQuantity_id=" + id +
                ", Magazyn=" + warehouse.getWarehouseName() +
                ", Produkt=" + product.getProductName() +
                ", Ilość=" + quantity +
                '}';
    }
}
