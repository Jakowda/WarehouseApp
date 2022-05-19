package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.User;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Repository.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> findAllWarehouses()
    {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    public List<Warehouse> getWarehousesListThatUserCanByAddedTo(User user) {
        List<Warehouse> usersWarehouseList = user.getWarehouses();
        System.out.println(usersWarehouseList);
        List<Warehouse> warehouseList = findAllWarehouses();
        System.out.println(warehouseList);
        for (Warehouse warehouse: usersWarehouseList) {
            for (Warehouse warehouse2: warehouseList) {
                if(warehouse2.getWarehouse_id().equals(warehouse.getWarehouse_id()))
                {
                    System.out.println(warehouse2);
                    warehouseList.remove(warehouseList.indexOf(warehouse2));
                }c 
            }
        }
        System.out.println(warehouseList);
        return warehouseList;
    }

//    public void addUserToWarehouse(Long userId, Long warehouseId)
//    {
//        warehouseRepository.addUserToWarehouse();
//
//    }
}
