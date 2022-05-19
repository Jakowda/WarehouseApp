package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Repository.SalesOrderLineRepository;
import pl.jakowicki.WarehouseApp.Repository.SalesOrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderService {
    private SalesOrderRepository salesOrderRepository;
    private ProductStockQuantityService productStockQuantityService;
    private SalesOrderLineRepository salesOrderLineRepository;

    public SalesOrderService(SalesOrderRepository salesOrderRepository, ProductStockQuantityService productStockQuantityService, SalesOrderLineRepository salesOrderLineRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.productStockQuantityService = productStockQuantityService;
        this.salesOrderLineRepository = salesOrderLineRepository;
    }

    public List<SalesOrder> showAllSalesOrders()
    {
        return (List<SalesOrder>) salesOrderRepository.findAll();
    }

    public SalesOrder prepareNewSalesOrder() {
        SalesOrder salesOrder = new SalesOrder();
        List<SalesOrderLine> salesOrderLineList = new ArrayList<>();
        salesOrderLineList.add(new SalesOrderLine());
        salesOrder.setSalesOrderLineList(salesOrderLineList);
        return salesOrder;
    }

    public SalesOrder assignSalesOrderIfSOIsNull(SalesOrder salesOrder) {
        List<SalesOrderLine> salesOrderLineList = salesOrder.getSalesOrderLineList();
        for (SalesOrderLine line: salesOrderLineList) {
            if(line.getSalesOrder()==null)
            {
                line.setSalesOrder(salesOrder);
            }
        }
        return salesOrder;
    }

    public void saveSalesOrder(SalesOrder salesOrder) {
        salesOrderRepository.save(salesOrder);
    }

    public void deleteSalesOrderByID(Long salesorderID) {
        salesOrderRepository.deleteById(salesorderID);
    }

    public SalesOrder findSalesOrderByID(Long salesorderID) {

        SalesOrder salesOrder = salesOrderRepository.findSalesOrderById(salesorderID);
        return salesOrder;
    }

    public void deleteSalesOrderLineByID(Long salesOrderLineID) {
        salesOrderLineRepository.deleteById(salesOrderLineID);
    }

    public void setSalesOrderAsCompletedAndUpdateStockQuantity(SalesOrder salesOrder) {
        Warehouse warehouse = salesOrder.getWarehouse();
        List<SalesOrderLine> salesOrderLineList = salesOrder.getSalesOrderLineList();
        List<ProductStockQuantity> productStockQuantityList = new ArrayList<>();
        for (SalesOrderLine line: salesOrderLineList) {
            Product product = line.getProduct();
            int amount = line.getOrderedAmount();
            ProductStockQuantity productStockQuantity = productStockQuantityService.getPSQByProductAndWarehouse(product, warehouse);
            int psqAmount = productStockQuantity.getQuantity();
            productStockQuantity.setQuantity(psqAmount - amount);
            productStockQuantityService.savePSQ(productStockQuantity);
        }
        salesOrder.setOrderStatus(true);
        salesOrderRepository.save(salesOrder);
    }

    public SalesOrder removeSalesOrderLinesAndUpdateSalesOrder(SalesOrder currentSalesOrder, int id) {
        List<SalesOrderLine> salesOrderLineList = currentSalesOrder.getSalesOrderLineList();
        if(salesOrderLineList.get(id).getId()!=null)
        {
            Long salesOrderLineID = salesOrderLineList.get(id).getId();
            deleteSalesOrderLineByID(salesOrderLineID);
        }
        salesOrderLineList.remove(id);
        currentSalesOrder.setSalesOrderLineList(salesOrderLineList);
        return currentSalesOrder;
    }

    public List<SalesOrder> showSalesOrders(List<Warehouse> warehouseList) {
        List<SalesOrder> salesOrderList = new ArrayList<>();
        for (Warehouse warehouse: warehouseList) {
            Long warehouseId = warehouse.getWarehouse_id();
            List<SalesOrder> foundSalesOrderList = salesOrderRepository.findAllSalesOrderByWarehouseId(warehouseId);
            for (SalesOrder salesOrder: foundSalesOrderList) {
                salesOrderList.add(salesOrder);
            }
        }
        return salesOrderList;
    }
}
