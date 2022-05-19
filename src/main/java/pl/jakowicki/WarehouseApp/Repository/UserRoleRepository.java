package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.jakowicki.WarehouseApp.Model.UserRole;
import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
