package pl.jakowicki.WarehouseApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "deliveryline")
public class OrderDeliveryLine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name = "id_product")
        private Product product;
        private int deliveredAmount;
        @ManyToOne
        @JoinColumn(name = "id_order_delivery")
        private OrderDelivery orderDelivery;

    public OrderDeliveryLine() {
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

    public int getDeliveredAmount() {
        return deliveredAmount;
    }

    public void setDeliveredAmount(int deliveredAmount) {
        this.deliveredAmount = deliveredAmount;
    }

    public OrderDelivery getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(OrderDelivery orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    @Override
    public String toString() {
        return "OrderDeliveryLine{" +
                "id=" + id +
                ", product=" + product +
                ", deliveredAmount=" + deliveredAmount +
                ", orderDelivery=" + orderDelivery.getId() +
                '}';
    }
}
