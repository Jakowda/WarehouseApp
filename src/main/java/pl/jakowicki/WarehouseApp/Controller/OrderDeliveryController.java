package pl.jakowicki.WarehouseApp.Controller;

import org.hibernate.criterion.Order;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderDeliveryController {
    private OrderDeliveryService orderDeliveryService;
    private ProductService productService;
    private WarehouseService warehouseService;
    private PurchaseOrderService purchaseOrderService;
    private VendorService vendorService;
    private UserService userService;

    public OrderDeliveryController(OrderDeliveryService orderDeliveryService, ProductService productService, WarehouseService warehouseService, PurchaseOrderService purchaseOrderService, VendorService vendorService, UserService userService) {
        this.orderDeliveryService = orderDeliveryService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.purchaseOrderService = purchaseOrderService;
        this.vendorService = vendorService;
        this.userService = userService;
    }

    @GetMapping("/show_delivery_list")
    public String showDeliveryList(Model model, Authentication authentication)
    {
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<OrderDelivery> orderDeliveryList = orderDeliveryService.showOrderDelivery(warehouseList);
        model.addAttribute("orderDeliveryList", orderDeliveryList);
        return "show_list_of_delivery";
    }

    @GetMapping("/add_new_delivery")
    public String addNewDelivery(Model model, Authentication authentication)
    {
        OrderDelivery orderDelivery = orderDeliveryService.prepareNewOrderDelivery();
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Product> productList = productService.showAllProducts();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("orderDelivery",orderDelivery);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productList);
        model.addAttribute("warehouseList", warehouseList);
        return "add_new_delivery";
    }

    @PostMapping(value="/save_new_delivery", params="add_line")
    String addDeliveryLine(@ModelAttribute(name = "orderDelivery") OrderDelivery currentOrderDelivery, Model model, Authentication authentication)
    {
        currentOrderDelivery.getDeliveryLineList().add(new OrderDeliveryLine());
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "add_new_delivery";
    }

    @PostMapping(value="/save_new_delivery", params="remove_line")
    String removePurchaseOrderLine(@ModelAttribute(name = "orderDelivery") OrderDelivery currentOrderDelivery, Model model, @RequestParam(name = "remove_line") int id, Authentication authentication)
    {
        List<OrderDeliveryLine> orderDeliveryLines  = currentOrderDelivery.getDeliveryLineList();
        orderDeliveryLines.remove(id);
        currentOrderDelivery.setDeliveryLineList(orderDeliveryLines);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "add_new_delivery";
    }

    @PostMapping(value="/save_new_delivery")
    String addDeliveryLine(@ModelAttribute(name = "orderDelivery") OrderDelivery orderDelivery)
    {

        List<OrderDeliveryLine> orderDeliveryLineList = orderDelivery.getDeliveryLineList();
        orderDeliveryService.updateStockQuantity(orderDeliveryLineList, orderDelivery.getWarehouse());
        List<OrderDeliveryLine> clearLine = new ArrayList<>();
        orderDelivery.setDeliveryLineList(clearLine);
        orderDeliveryService.saveOrderDelivery(orderDelivery);
        Long orderDeliveryID = orderDeliveryService.findOrderDeliveryID(orderDelivery.getDeliveryName());
        orderDelivery.setId(orderDeliveryID);
        for (OrderDeliveryLine line : orderDeliveryLineList) {
           line.setOrderDelivery(orderDelivery);
        }
        orderDeliveryService.saveAllOrderDeliveryLines(orderDeliveryLineList);
        return "redirect:/show_delivery_list";
    }

    @GetMapping(value="/edit_delivery/{deliveryID}")
    public String showDeliveryEditForm(Model model, @PathVariable(name ="deliveryID") Long deliveryID, Authentication authentication)
    {
        OrderDelivery orderDelivery = orderDeliveryService.findOrderDeliveryByID(deliveryID);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Product> productList = productService.showAllProducts();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("orderDelivery", orderDelivery);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_delivery";
    }

    @PostMapping(value="/save_edited_delivery")
    public String savePurchaseOrder(@ModelAttribute(name = "orderDelivery") OrderDelivery orderDelivery) {
        orderDelivery = orderDeliveryService.assignOrderDeliveryIfOrderDeliveryIdIsNull(orderDelivery);
        orderDeliveryService.saveOrderDelivery(orderDelivery);
        return "redirect:/show_delivery_list";
    }

    @PostMapping(value="/save_edited_delivery", params="add_line")
    public String addDeliveryLineOnEditForm(@ModelAttribute(name = "orderDelivery") OrderDelivery currentOrderDelivery, Model model, Authentication authentication)
    {
        currentOrderDelivery.getDeliveryLineList().add(new OrderDeliveryLine());
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_delivery";
    }

    @PostMapping(value="/save_edited_delivery", params="remove_line")
    public String removePurchaseOrderLineOnEditForm(@ModelAttribute(name = "orderDelivery") OrderDelivery currentOrderDelivery, Model model, @RequestParam(name = "remove_line") int id, Authentication authentication)
    {
        List<OrderDeliveryLine> orderDeliveryLines  = currentOrderDelivery.getDeliveryLineList();
        if(orderDeliveryLines.get(id).getId()!=null)
        {
            Long orderDeliveryLinesID = orderDeliveryLines.get(id).getId();
            System.out.println(orderDeliveryLinesID);
            orderDeliveryService.deleteDeliveryLine(orderDeliveryLinesID);
        }
        orderDeliveryLines.remove(id);
        currentOrderDelivery.setDeliveryLineList(orderDeliveryLines);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showAllPurchaseOrders();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(authentication.getName());
        List<Product> productsList = productService.showAllProducts();
        List<Vendor> vendorList = vendorService.findAllVendors();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        model.addAttribute("productList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_delivery";
    }

    @GetMapping(value="/delete_delivery/{delivery_id}")
    public String deleteOrderDelivery(@PathVariable(name = "delivery_id") Long id)
    {
        orderDeliveryService.deleteOrderDelivery(id);
        return "redirect:/show_delivery_list";
    }
}
