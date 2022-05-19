package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Repository.DeliveryLineRepository;
import pl.jakowicki.WarehouseApp.Repository.OrderDeliveryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDeliveryService {
    private OrderDeliveryRepository orderDeliveryRepository;
    private DeliveryLineRepository deliveryLineRepository;
    private ProductStockQuantityService productStockQuantityService;


    public OrderDeliveryService(OrderDeliveryRepository orderDeliveryRepository, DeliveryLineRepository deliveryLineRepository, ProductStockQuantityService productStockQuantityService) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.deliveryLineRepository = deliveryLineRepository;
        this.productStockQuantityService = productStockQuantityService;
    }

    public List<OrderDelivery> showAllOrderDelivery() {
        return (List<OrderDelivery>) orderDeliveryRepository.findAll();
    }
    public void deleteDeliveryLine(Long line_id)
    {
        deliveryLineRepository.deleteById(line_id);
    }

    public void saveOrderDelivery(OrderDelivery orderDelivery)
    {
        orderDeliveryRepository.save(orderDelivery);
    }

    public OrderDelivery prepareNewOrderDelivery()
    {
        OrderDelivery orderDelivery = new OrderDelivery();
        OrderDeliveryLine orderDeliveryLine = new OrderDeliveryLine();
        List<OrderDeliveryLine> orderDeliveryLines = orderDelivery.getDeliveryLineList();
        orderDeliveryLines.add(orderDeliveryLine);
        orderDelivery.setDeliveryLineList(orderDeliveryLines);
        return orderDelivery;
    }

    public Long findOrderDeliveryID(String orderDeliveryName)
    {
        OrderDelivery orderDelivery = orderDeliveryRepository.findAllByDeliveryName(orderDeliveryName);
        return orderDelivery.getId();
    }

    public void saveAllOrderDeliveryLines(List<OrderDeliveryLine> orderDeliveryLineList)
    {
        deliveryLineRepository.saveAll(orderDeliveryLineList);
    }

    public OrderDelivery findOrderDeliveryByID(Long id)
    {
       OrderDelivery orderDelivery = orderDeliveryRepository.findAllById(id);
       return orderDelivery;
    }
    public OrderDelivery assignOrderDeliveryIfOrderDeliveryIdIsNull(OrderDelivery orderDelivery)
    {
        List<OrderDeliveryLine> orderDeliveryLinesList = orderDelivery.getDeliveryLineList();
        for (OrderDeliveryLine line: orderDeliveryLinesList) {
            if(line.getOrderDelivery()==null)
            {
                line.setOrderDelivery(orderDelivery);
            }
        }
        orderDelivery.setDeliveryLineList(orderDeliveryLinesList);
        return orderDelivery;
    }

    public void deleteOrderDelivery(Long delivery_id)
    {
        orderDeliveryRepository.deleteById(delivery_id);
    }

    public void updateStockQuantity( List<OrderDeliveryLine> orderDeliveryLineList, Warehouse warehouse) {
        for (OrderDeliveryLine line: orderDeliveryLineList) {
            System.out.println(orderDeliveryLineList.size());
            Product product = line.getProduct();
            int deliveryAmount = line.getDeliveredAmount();
            ProductStockQuantity productStockQuantity;
            Long productStockQuantityID = productStockQuantityService.findStockQuantityDBID(product.getId(), warehouse.getWarehouse_id());
            ProductStockQuantity productStockQuantityPotential = new ProductStockQuantity(warehouse, product, deliveryAmount);
            if(productStockQuantityID!=null)
            {
                productStockQuantity = productStockQuantityService.getPSQByProductAndWarehouse(product, warehouse);
                int psqAmount = productStockQuantity.getQuantity();
                System.out.println("1"+productStockQuantity);
                productStockQuantity.setQuantity(psqAmount+deliveryAmount);

            }else
            {
                productStockQuantity = productStockQuantityPotential;
            }
            productStockQuantityService.savePSQ(productStockQuantity);
        }
    }

    public List<OrderDelivery> showOrderDelivery(List<Warehouse> warehouseList) {
        List<OrderDelivery> orderDeliveryList = new ArrayList<>();
        for (Warehouse warehouse: warehouseList) {
            Long warehouseId = warehouse.getWarehouse_id();
            List<OrderDelivery> foundOrderDeliveryList = orderDeliveryRepository.getOrderDeliveryByWarehouseId(warehouseId);
            for (OrderDelivery orderDelivery : foundOrderDeliveryList) {
                orderDeliveryList.add(orderDelivery);
            }
        }
        return orderDeliveryList;
    }
}
