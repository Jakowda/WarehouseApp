package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.Vendor;

@Repository
public interface VendorsRepository extends JpaRepository<Vendor, Long> {
}
