package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "purchaseorderline")
public class PurchaseOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseorderline_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private int orderAmount;
    private double purchasePrice;
    @ManyToOne
    @JoinColumn(name = "id_purchase_order")
    private PurchaseOrder purchaseOrder;

    public PurchaseOrderLine() {
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

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Override
    public String toString() {
        return "PurchaseOrderLine{" +
                "id=" + id +
                ", product=" + product +
                ", orderAmount=" + orderAmount +
                ", purchasePrice=" + purchasePrice +
                ", purchaseOrder=" + purchaseOrder.getId() +
                '}';
    }
}
