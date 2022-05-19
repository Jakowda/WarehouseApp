package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private double unitStandardPrice;
    @ManyToOne
    @JoinColumn(name = "id_unit")
    private Unit unit;


    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitStandardPrice() {
        return unitStandardPrice;
    }

    public void setUnitStandardPrice(double unitStandardPrice) {
        this.unitStandardPrice = unitStandardPrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", productName='" + productName + '\'' +
                ", unitStandardPrice=" + unitStandardPrice +
                ", id_unit=" + unit +
                '}';
    }
}
