package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jakowicki.WarehouseApp.Model.*;
import pl.jakowicki.WarehouseApp.Service.PurchaseOrderService;
import pl.jakowicki.WarehouseApp.Service.UserService;
import pl.jakowicki.WarehouseApp.Service.VendorService;

import java.util.List;


@Controller
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;
    private VendorService vendorService;
    private UserService userService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService, VendorService vendorService, UserService userService) {
        this.purchaseOrderService = purchaseOrderService;
        this.vendorService = vendorService;
        this.userService = userService;
    }

    @GetMapping("/purchaseorder_list")
    public String showAllPurchaseOrders(Model model, Authentication authentication)
    {
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.showPurchaseOrders(warehouseList);
        model.addAttribute("purchaseOrderList", purchaseOrderList);
        return "show_list_of_purchase_order";
    }

    @GetMapping("/add_purchase_order")
    public String showAddPurchaseOrderForm(Authentication authentication, Model model)
    {
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productsList = purchaseOrderService.showAllProducts();
        PurchaseOrder purchaseOrder = purchaseOrderService.prepareNewPurchaseOrder();
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);

        return "add_purchase_order";
    }

    @PostMapping(value="/save_edited_purchaseorder")
    public String savePurchaseOrder(@ModelAttribute(name = "purchaseOrder") PurchaseOrder purchaseOrder) {
        purchaseOrder = purchaseOrderService.assignPurchaseOrderIfPoIdIsNull(purchaseOrder);
        purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return "redirect:/purchaseorder_list";
    }

    @GetMapping("/edit_purchase_order/{purchaseOrderId}")
    public String editPurchaseOrder(Authentication authentication,Model model, @PathVariable(name = "purchaseOrderId")Long purchaseOrderID)
    {
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);

        List<Product> productsList = purchaseOrderService.showAllProducts();
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrderByID(purchaseOrderID);
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_purchase_order";
    }

    @PostMapping(value="/save_edited_purchaseorder", params="add_line")
    String addPurchaseOrderLine(@ModelAttribute(name = "purchaseOrder") PurchaseOrder currentPurchaseOrder,Model model, Authentication authentication)
    {
        currentPurchaseOrder.getPurchaseOrderLineList().add(new PurchaseOrderLine());
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_purchase_order";
    }

    @PostMapping(value="/save_edited_purchaseorder", params="remove_line")
    String removePurchaseOrderLine(@ModelAttribute(name = "purchaseOrder") PurchaseOrder currentPurchaseOrder,@RequestParam(name = "remove_line") int id, Model model, Authentication authentication)
    {
        List<PurchaseOrderLine> purchaseOrderLine = currentPurchaseOrder.getPurchaseOrderLineList();
        if(purchaseOrderLine.get(id).getId()!=null)
        {
            Long purchaseOrderLineID = purchaseOrderLine.get(id).getId();
            purchaseOrderService.deletePurchaseOrderLine(purchaseOrderLineID);
        }
        purchaseOrderLine.remove(id);
        currentPurchaseOrder.setPurchaseOrderLineList(purchaseOrderLine);
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "edit_purchase_order";
    }


    @GetMapping("/delete_purchase_order/{purchaseOrderId}")
    public String editPurchaseOrder(@PathVariable(name = "purchaseOrderId")Long purchaseOrderID) {
        purchaseOrderService.deletePurchaseOrder(purchaseOrderID);
        return "redirect:/purchaseorder_list";
    }

    @PostMapping(value="/save_new_purchaseorder")
    public String saveNewPurchaseOrder(@ModelAttribute(name = "purchaseOrder") PurchaseOrder purchaseOrder) {
       purchaseOrder =  purchaseOrderService.assignPurchaseOrderIfPoIdIsNull(purchaseOrder);
       purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return "redirect:/purchaseorder_list";
    }

    @PostMapping(value="/save_new_purchaseorder", params="add_line")
    String addPurchaseOrderLineOnNewPO(@ModelAttribute(name = "purchaseOrder") PurchaseOrder currentPurchaseOrder,Model model, Authentication authentication)
    {
        currentPurchaseOrder.getPurchaseOrderLineList().add(new PurchaseOrderLine());
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "add_purchase_order";
    }

    @PostMapping(value="/save_new_purchaseorder", params="remove_line")
    String removePurchaseOrderLineOnNewPO(@ModelAttribute(name = "purchaseOrder") PurchaseOrder currentPurchaseOrder,@RequestParam(name = "remove_line") int id, Model model, Authentication authentication)
    {
        List<PurchaseOrderLine> purchaseOrderLine = currentPurchaseOrder.getPurchaseOrderLineList();
        purchaseOrderLine.remove(id);
        String userEmail = authentication.getName();
        List<Warehouse> warehouseList = userService.getListOfUsersWarehousesByUserEmail(userEmail);
        List<Product> productsList = purchaseOrderService.showAllProducts();
        List <Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        model.addAttribute("productsList", productsList);
        model.addAttribute("warehouseList", warehouseList);
        return "add_purchase_order";
    }
}
