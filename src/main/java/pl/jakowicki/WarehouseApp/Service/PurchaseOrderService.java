package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Repository.ProductRepository;
import pl.jakowicki.WarehouseApp.Repository.PurchaseOrderLineRepository;
import pl.jakowicki.WarehouseApp.Repository.PurchaseOrderRepository;
import pl.jakowicki.WarehouseApp.Repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderLineRepository purchaseOrderLineRepository;
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderLineRepository purchaseOrderLineRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderLineRepository = purchaseOrderLineRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public List<PurchaseOrder> showAllPurchaseOrders()
    {
        return (List<PurchaseOrder>) purchaseOrderRepository.findAll();
    }

    public List<PurchaseOrder> showPurchaseOrders(List<Warehouse> warehouseList)
    {
      List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        for (Warehouse warehouse: warehouseList) {
            Long warehouseId = warehouse.getWarehouse_id();
            List<PurchaseOrder> foundPurchaseOrderList = purchaseOrderRepository.findAllPurchaseOrderByWarehouseId(warehouseId);
            for (PurchaseOrder purchaseOrder: foundPurchaseOrderList) {
                purchaseOrderList.add(purchaseOrder);
            }
        }
      return purchaseOrderList;
    }

    public List<Product> showAllProducts()
    {
        return productRepository.findAll();
    }

    public PurchaseOrder findPurchaseOrderByID(Long id)
    {
        return purchaseOrderRepository.findAllById(id);
    }

    public void savePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrderRepository.save(purchaseOrder);
    }

//    public void deletePurchaseOrderLineIfExist(Long line_id)
//    {
//        Boolean isExist = purchaseOrderLineRepository.existsById(line_id);
//        if(isExist)
//        {
//            purchaseOrderLineRepository.deleteById(line_id);
//        }
//    }
    public void deletePurchaseOrderLine(Long line_id)
    {
        purchaseOrderLineRepository.deleteById(line_id);
    }

    public void deletePurchaseOrder(Long purchaseOrder_id)
    {
        purchaseOrderRepository.deleteById(purchaseOrder_id);
    }

    public Long findPurchaseOrderID(String purchaseOrderName)
    {
       PurchaseOrder purchaseOrder= purchaseOrderRepository.findAllByPurchaseOrderName(purchaseOrderName);
       return purchaseOrder.getId();
    }

    public void saveAllPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLineList)
    {
        purchaseOrderLineRepository.saveAll(purchaseOrderLineList);
    }

    public PurchaseOrder prepareNewPurchaseOrder()
    {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrder.getPurchaseOrderLineList();
        purchaseOrderLineList.add(purchaseOrderLine);
        purchaseOrder.setPurchaseOrderLineList(purchaseOrderLineList);
        return purchaseOrder;
    }

    public List<Warehouse> showAllWarehouses()
    {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    public PurchaseOrder assignPurchaseOrderIfPoIdIsNull(PurchaseOrder purchaseOrder){
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrder.getPurchaseOrderLineList();
        for (PurchaseOrderLine line: purchaseOrderLineList) {
            if(line.getPurchaseOrder()==null)
            {
                line.setPurchaseOrder(purchaseOrder);
            }
        }
        purchaseOrder.setPurchaseOrderLineList(purchaseOrderLineList);
        return purchaseOrder;
    }


}

