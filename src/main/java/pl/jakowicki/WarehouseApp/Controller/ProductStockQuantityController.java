package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakowicki.WarehouseApp.DTO.SearchDTO;
import pl.jakowicki.WarehouseApp.Model.Product;
import pl.jakowicki.WarehouseApp.Model.ProductStockQuantity;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Service.ProductService;
import pl.jakowicki.WarehouseApp.Service.ProductStockQuantityService;
import pl.jakowicki.WarehouseApp.Service.UserService;
import pl.jakowicki.WarehouseApp.Service.WarehouseService;

import java.util.List;

@Controller
public class ProductStockQuantityController {

    private ProductStockQuantityService productStockQuantityService;
    private ProductService productService;
    private WarehouseService warehouseService;
    private UserService userService;

    public ProductStockQuantityController(ProductStockQuantityService productStockQuantityService, ProductService productService, WarehouseService warehouseService, UserService userService) {
        this.productStockQuantityService = productStockQuantityService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.userService = userService;
    }

    @GetMapping(value = "show_all_products_stock_value")
    public String showAllProductsStockValue(Model model, Authentication authentication)
    {
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<ProductStockQuantity> productStockQuantityList =  productStockQuantityService.showEveryProductStockValueFromUsersWarehouses(warehouseList);
        List<Product> productList = productService.showAllProducts();
        model.addAttribute("productStockQuantityList", productStockQuantityList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("productList", productList);
        SearchDTO searchDTO = new SearchDTO();
        model.addAttribute("searchDTO", searchDTO);
        return "show_list_of_products_stock";
    }

    @PostMapping(value = "show_stock_quantity_from_warehouse")
    public String showAllProductsStockValueFromWarehouse(SearchDTO searchDTO, Model model, Authentication authentication)
    {
        List<ProductStockQuantity> productStockQuantityList =  productStockQuantityService.findAllStockQuantityFromWarehouse(searchDTO.getWarehouse());
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Product> productList = productService.showAllProducts();
        model.addAttribute("productStockQuantityList", productStockQuantityList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("productList", productList);
        return "show_list_of_products_stock";
    }

    @PostMapping(value = "show_stock_quantity_of_products")
    public String showAllProductsStockValueByProduct(SearchDTO searchDTO, Model model, Authentication authentication)
    {
          List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<ProductStockQuantity> productStockQuantityList =  productStockQuantityService.getPSQByProductAndUsersWarehouses(searchDTO.getProduct(), warehouseList);
        System.out.println(productStockQuantityList);
        List<Product> productList = productService.showAllProducts();
        model.addAttribute("productStockQuantityList", productStockQuantityList);
       model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("productList", productList);
        return "show_list_of_products_stock";
    }
}
