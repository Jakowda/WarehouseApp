package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Model.Unit;
import pl.jakowicki.WarehouseApp.Repository.UnitRepository;
import pl.jakowicki.WarehouseApp.Service.ProductService;
import pl.jakowicki.WarehouseApp.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;
    private UnitRepository unitRepository;
    private UserService userService;

    public ProductController(ProductService productService, UnitRepository unitRepository, UserService userService) {
        this.productService = productService;
        this.unitRepository = unitRepository;
        this.userService = userService;
    }

    @GetMapping("/allprod")
    public String showAllProducts(Model model)
    {
        List<Product> listOfProducts = productService.showAllProducts();
        model.addAttribute("listOfProducts", listOfProducts);
        return "show_list_of_products";
    }

    @GetMapping("/addprod")
    public String addNewProduct(Model model)
    {
        Product product = new Product();
        List<Unit> unitList = (List<Unit>) unitRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("unitList", unitList);
        return "add_product";
    }

    @PostMapping("/save")
    public String saveNewProduct(Product product)
    {
        productService.saveNewProduct(product);
        return "redirect:/allprod";
    }

    @GetMapping("/edit_product/{id}")
    public String editProduct(Model model, @PathVariable(name = "id") Long productID)
    {
        Optional<Product> product =  productService.findProductById(productID);
        List<Unit> unitList = (List<Unit>) unitRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("unitList", unitList);
        return "edit_product";
    }

    @GetMapping("/save_edited_product/{id}")
    public String saveEditedProduct(Model model, Product product, @PathVariable(name = "id") Long id)
    {
        product.setId(id);
        productService.saveEditedProduct(product);
        return "redirect:/allprod";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/allprod";
    }
}
