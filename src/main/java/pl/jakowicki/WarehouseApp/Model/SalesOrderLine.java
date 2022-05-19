package pl.jakowicki.WarehouseApp.Model;


import javax.persistence.*;

@Entity
@Table(name ="salesorderline")
public class SalesOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private int orderedAmount;
    private double salesPrice;
    @ManyToOne
    @JoinColumn(name = "id_sales_order")
    private SalesOrder salesOrder;

    public SalesOrderLine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(int orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Override
    public String toString() {
        return "SalesOrderLine{" +
                "id=" + id +
                ", product=" + product +
                ", unitsOrdered=" + orderedAmount +
                ", salesPrice=" + salesPrice +
                ", salesOrder=" + salesOrder +
                '}';
    }
}
