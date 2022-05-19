package pl.jakowicki.WarehouseApp.Service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Model.ProductStockQuantity;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Repository.ProductStockQuantityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStockQuantityService {

    private final ProductStockQuantityRepository productStockQuantityRepository;

    public ProductStockQuantityService(ProductStockQuantityRepository productStockQuantityRepository) {
        this.productStockQuantityRepository = productStockQuantityRepository;
    }

    public List<ProductStockQuantity> showEveryProductStockValue() {
        List<ProductStockQuantity> productStockQuantityList = (List<ProductStockQuantity>) productStockQuantityRepository.findAll();
        return productStockQuantityList;
    }

    public List<ProductStockQuantity> showEveryProductStockValueFromUsersWarehouses(List<Warehouse> warehouseList)
    {
        List<ProductStockQuantity> productStockQuantityList = new ArrayList<>();
        for (Warehouse warehouse: warehouseList) {
            Long warehouseID = warehouse.getWarehouse_id();
            List<ProductStockQuantity> foundProductStockQuantityList = productStockQuantityRepository.findAllSockQuantityFromWarehouse(warehouseID);
            for (ProductStockQuantity psq: foundProductStockQuantityList) {
                productStockQuantityList.add(psq);
            }
        }
        return productStockQuantityList;
    }

    public List<ProductStockQuantity> findAllStockQuantityFromWarehouse(Warehouse warehouse)
    {
        List<ProductStockQuantity> productStockQuantityList = productStockQuantityRepository.findAllSockQuantityFromWarehouse(warehouse.getWarehouse_id());
        return productStockQuantityList;
    }

    public List<ProductStockQuantity> findAllStockQuantityByProduct(Product product) {
        List<ProductStockQuantity> productStockQuantityList = productStockQuantityRepository.findAllSockQuantityByProduct(product.getId());
        return productStockQuantityList;
    }

    public ProductStockQuantity getPSQByProductAndWarehouse(Product product, Warehouse warehouse) {
        return productStockQuantityRepository.findAllStockQuantityByProductAndWarehouse(product.getId(), warehouse.getWarehouse_id());
    }

    public List<ProductStockQuantity> getPSQByProductAndUsersWarehouses(Product product, List<Warehouse> warehouseList) {
        List<ProductStockQuantity> productStockQuantityList = new ArrayList<>();
        for (Warehouse warehouse: warehouseList) {
            Long warehouseID = warehouse.getWarehouse_id();
            ProductStockQuantity foundProductStockQuantity = productStockQuantityRepository.findAllStockQuantityByProductAndWarehouse(product.getId(), warehouseID);
            if(foundProductStockQuantity!= null)
            productStockQuantityList.add(foundProductStockQuantity);
        }
        return productStockQuantityList;
    }

    public void savePSQ(ProductStockQuantity productStockQuantity) {
        productStockQuantityRepository.save(productStockQuantity);
    }

    public boolean isStockQuantityExist(ProductStockQuantity productStockQuantityPotential) {
        return productStockQuantityRepository.exists(Example.of(productStockQuantityPotential));
    }

    public Long findStockQuantityDBID(Long product_id, Long warehouse_id) {
        return productStockQuantityRepository.findIdByProductAndWarehouse(product_id, warehouse_id);
    }
}
