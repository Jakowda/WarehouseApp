package pl.jakowicki.WarehouseApp.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salesorder")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String salesOrderName;
    private String salesOrderDate;
    private String fullfilmentDate;
    private Boolean orderStatus;
    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    private List<SalesOrderLine> salesOrderLineList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Customer client;

    public Customer getClient() {
        return client;
    }

    public SalesOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(String salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }


    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getSalesOrderName() {
        return salesOrderName;
    }

    public void setSalesOrderName(String salesOrderName) {
        this.salesOrderName = salesOrderName;
    }

    public String getFullfilmentDate() {
        return fullfilmentDate;
    }

    public void setFullfilmentDate(String fullfilmentDate) {
        this.fullfilmentDate = fullfilmentDate;
    }

    public List<SalesOrderLine> getSalesOrderLineList() {
        return salesOrderLineList;
    }

    public void setSalesOrderLineList(List<SalesOrderLine> salesOrderLineList) {
        this.salesOrderLineList = salesOrderLineList;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "SalesOrder{" +
                "id=" + id +
                ", salesOrderName='" + salesOrderName + '\'' +
                ", salesOrderDate=" + salesOrderDate +
                ", dateOfFulfillment=" + fullfilmentDate +
                ", orderStatus=" + orderStatus +
                ", warehouse=" + warehouse +
                '}';
    }
}


