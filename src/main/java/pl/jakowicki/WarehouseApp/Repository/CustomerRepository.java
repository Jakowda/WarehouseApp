package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
