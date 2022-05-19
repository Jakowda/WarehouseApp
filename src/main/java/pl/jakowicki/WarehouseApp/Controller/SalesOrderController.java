package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Service.*;

import java.util.List;

@Controller
public class SalesOrderController {

    private SalesOrderService salesOrderService;
    private ProductService productService;
    private WarehouseService warehouseService;
    private ProductStockQuantityService productStockQuantityService;
    private CustomerService clientService;
    private UserService userService;

    public SalesOrderController(SalesOrderService salesOrderService, ProductService productService, WarehouseService warehouseService, ProductStockQuantityService productStockQuantityService, CustomerService clientService, UserService userService) {
        this.salesOrderService = salesOrderService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.productStockQuantityService = productStockQuantityService;
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping(value = "/show_all_sales_orders")
    public String showAllSalesOrders(Model model, Authentication authentication)
    {
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        model.addAttribute("salesOrderList", salesOrderList);
        return "/show_list_of_sales_orders";
    }

    @GetMapping(value = "/add_new_salesorder")
    public String showNewSalesOrderForm(Model model, Authentication authentication)
    {
        SalesOrder salesOrder = salesOrderService.prepareNewSalesOrder();
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("salesOrder", salesOrder);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("productList", productList);
        model.addAttribute("clientList", clientList);
        return "/add_salesorder";
    }

    @GetMapping(value="/delete_sales_order/{salesorderID}")
    public String deleteSalesOrder(@PathVariable(name = "salesorderID") Long salesorderID)
    {
        salesOrderService.deleteSalesOrderByID(salesorderID);
        return "redirect:/show_all_sales_orders";
    }

    @GetMapping(value="/edit_sales_order/{salesorderID}")
    public String editSalesOrderByID(@PathVariable(name = "salesorderID") Long salesorderID, Model model, Authentication authentication)
    {
        SalesOrder salesOrder = salesOrderService.findSalesOrderByID(salesorderID);
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        List<Product> productList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("salesOrder", salesOrder);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("productList", productList);
        model.addAttribute("clientList", clientList);
        return "edit_salesorder";
    }

    @PostMapping(value = "/save_edited_salesorder")
    public String saveEditedSalesOrder(@ModelAttribute("salesOrder") SalesOrder salesOrder)
    {
        salesOrder = salesOrderService.assignSalesOrderIfSOIsNull(salesOrder);
        salesOrderService.saveSalesOrder(salesOrder);
        return  "redirect:/show_all_sales_orders";
    }

    @PostMapping(value="/save_edited_salesorder", params="add_line")
    String addSalesOrderLine(@ModelAttribute(name = "salesOrder") SalesOrder currentSalesOrder,Model model, Authentication authentication)
    {

        currentSalesOrder.getSalesOrderLineList().add(new SalesOrderLine());
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        List<Product> productsList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("clientList", clientList);
        return "edit_salesorder";
    }

    @PostMapping(value="/save_edited_salesorder", params="remove_line")
    String removeSalesOrderLine(@ModelAttribute(name = "salesOrder") SalesOrder currentSalesOrder,@RequestParam(name = "remove_line") int id, Model model, Authentication authentication)
    {
        salesOrderService.removeSalesOrderLinesAndUpdateSalesOrder(currentSalesOrder, id);
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        List<Product> productsList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("clientList", clientList);
        return "edit_salesorder";
    }

    @PostMapping(value = "/save_new_salesorder")
    public String saveNewSalesOrder(@ModelAttribute("salesOrder") SalesOrder salesOrder)
    {
        salesOrder = salesOrderService.assignSalesOrderIfSOIsNull(salesOrder);
        salesOrderService.saveSalesOrder(salesOrder);
        return  "redirect:/show_all_sales_orders";
    }

    @PostMapping(value="/save_new_salesorder", params="add_line")
    String addSalesOrderLineOnNewSO(@ModelAttribute(name = "salesOrder") SalesOrder currentSalesOrder, Model model, Authentication authentication)
    {
        currentSalesOrder.getSalesOrderLineList().add(new SalesOrderLine());
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        List<Product> productsList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("clientList", clientList);
        return "add_salesorder";
    }


    @PostMapping(value="/save_new_salesorder", params="remove_line")
    String removeSalesOrderLineOnNewSO(@ModelAttribute(name = "salesOrder") SalesOrder currentSalesOrder,@RequestParam(name = "remove_line") int id, Model model, Authentication authentication)
    {
        List<SalesOrderLine> salesOrderLineList = currentSalesOrder.getSalesOrderLineList();
        salesOrderLineList.remove(id);
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<SalesOrder> salesOrderList = salesOrderService.showSalesOrders(warehouseList);
        List<Product> productsList = productService.showAllProducts();
        List<Customer> clientList = clientService.findAllCustomers();
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("clientList", clientList);
        return "add_salesorder";
    }


    @GetMapping(value = "/set_salesorder_as_done/{salesorderID}")
    public String setSalesOrderAsCompleted(@PathVariable(name = "salesorderID") Long salesoderID)
    {
        SalesOrder salesOrder = salesOrderService.findSalesOrderByID(salesoderID);
        salesOrderService.setSalesOrderAsCompletedAndUpdateStockQuantity(salesOrder);
        return "redirect:/show_all_sales_orders";
    }
}
