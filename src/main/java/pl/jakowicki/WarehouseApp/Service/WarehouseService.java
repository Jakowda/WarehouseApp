package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

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

    public Warehouse findById(Long warehouseId) {
        return warehouseRepository.findWarehouseById(warehouseId);
    }

    public void saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }


//    public void addUserToWarehouse(Long userId, Long warehouseId)
//    {
//        warehouseRepository.addUserToWarehouse();
//
//    }
}
