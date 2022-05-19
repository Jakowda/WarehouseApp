package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showAllProducts()
    {
        List<Product> listOfProducts = (List<Product>) productRepository.findAll();
        return listOfProducts;
    }

    public void saveNewProduct(Product product)
    {
        productRepository.save(product);
    }

    public Optional<Product> findProductById(Long productID)
    {
       Optional<Product> product = productRepository.findById(productID);
       return  product;
    }

    public void saveEditedProduct(Product product)
    {
        productRepository.save(product);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }
}
