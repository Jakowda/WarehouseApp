package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.UserToWarehouse;
import pl.jakowicki.WarehouseApp.Repository.UserToWarehouseRepository;

@Service
public class UserToWarehouseService {

    private UserToWarehouseRepository userToWarehouseRepository;

    public UserToWarehouseService(UserToWarehouseRepository userToWarehouseRepository) {
        this.userToWarehouseRepository = userToWarehouseRepository;
    }

    public UserToWarehouse ifNullThenAddNewRelation(Long warehouseId, Long userId) {
        UserToWarehouse userToWarehouse = userToWarehouseRepository.findAllByWarehouseAndUserID(warehouseId, userId);
        return userToWarehouse;
    }

    public void saveNewRelation(UserToWarehouse userToWarehouse) {
        userToWarehouseRepository.save(userToWarehouse);
    }

    public void deleteUserWarehouseConnection(UserToWarehouse userToWarehouse) {
        userToWarehouseRepository.delete(userToWarehouse);
    }

    public UserToWarehouse findRelationByWarehouseAndUserId(Long warehouseId, Long userId) {
        return userToWarehouseRepository.findAllByWarehouseAndUserID(warehouseId, userId);
    }
}
