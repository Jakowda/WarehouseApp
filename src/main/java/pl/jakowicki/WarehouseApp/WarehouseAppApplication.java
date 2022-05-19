package pl.jakowicki.WarehouseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Model.ProductStockQuantity;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Repository.ProductRepository;
import pl.jakowicki.WarehouseApp.Repository.ProductStockQuantityRepository;
import pl.jakowicki.WarehouseApp.Repository.WarehouseRepository;
import pl.jakowicki.WarehouseApp.Service.ProductService;

import java.util.List;

@SpringBootApplication
public class WarehouseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseAppApplication.class, args);
	}

}
