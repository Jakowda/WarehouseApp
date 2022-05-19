package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {

}
