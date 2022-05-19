package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakowicki.WarehouseApp.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
