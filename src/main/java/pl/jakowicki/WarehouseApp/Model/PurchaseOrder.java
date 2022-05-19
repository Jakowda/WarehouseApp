package pl.jakowicki.WarehouseApp.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String purchaseOrderName;
    private String purchaseOrderDate;
    private String fullfilment_date;
    private Boolean orderState;
    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderLine> purchaseOrderLineList =new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_vendor")
    private Vendor vendor;

    public Vendor getVendor() {
        return vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public String getFullfilment_date() {
        return fullfilment_date;
    }

    public void setFullfilment_date(String fullfilment_date) {
        this.fullfilment_date = fullfilment_date;
    }

    public Boolean getOrderState() {
        return orderState;
    }

    public void setOrderState(Boolean orderState) {
        this.orderState = orderState;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<PurchaseOrderLine> getPurchaseOrderLineList() {
        return purchaseOrderLineList;
    }

    public void setPurchaseOrderLineList(List<PurchaseOrderLine> purchaseOrderLineList) {
        this.purchaseOrderLineList = purchaseOrderLineList;
    }

    public String getPurchaseOrderName() {
        return purchaseOrderName;
    }

    public void setPurchaseOrderName(String purchaseOrderName) {
        this.purchaseOrderName = purchaseOrderName;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", purchaseOrderName='" + purchaseOrderName + '\'' +
                ", purchaseOrderDate='" + purchaseOrderDate + '\'' +
                ", fullfilment_date='" + fullfilment_date + '\'' +
                ", orderState=" + orderState +
                ", warehouse=" + warehouse +
                ", purchaseOrderLineList=" + purchaseOrderLineList +
                '}';
    }
}
