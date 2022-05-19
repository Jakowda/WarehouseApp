package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderdelivery")
public class OrderDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "delivery_name")
    private String deliveryName;
    @ManyToOne
    @JoinColumn(name = "id_purchase_order")
    private PurchaseOrder purchaseOrder;
    private String deliveryDate;
    @ManyToOne()
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "orderDelivery", cascade = CascadeType.ALL)
    private List<OrderDeliveryLine> deliveryLineList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_vendor")
    private Vendor vendor;

    public Vendor getVendor() {
        return vendor;
    }

    public OrderDelivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<OrderDeliveryLine> getDeliveryLineList() {
        return deliveryLineList;
    }

    public void setDeliveryLineList(List<OrderDeliveryLine> deliveryLineList) {
        this.deliveryLineList = deliveryLineList;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "OrderDelivery{" +
                "id=" + id +
                ", deliveryName='" + deliveryName + '\'' +
                ", purchaseOrder=" + purchaseOrder +
                ", deliveryDate=" + deliveryDate +
                ", warehouse=" + warehouse +
                ", deliveryLineList=" + deliveryLineList +
                '}';
    }
}
