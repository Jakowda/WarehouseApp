package pl.jakowicki.WarehouseApp.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakowicki.WarehouseApp.Model.Vendor;
import pl.jakowicki.WarehouseApp.Service.VendorService;

import java.util.List;

@Controller
public class VendorsController {

    private VendorService vendorService;

    public VendorsController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping(value = "show_all_vendors")
    public String ShowListOfVendors(Model model)
    {
        List<Vendor> vendorsList = vendorService.findAllVendors();
        model.addAttribute("vendorsList", vendorsList);
        return "show_list_of_vendors";
    }


    @GetMapping(value = "add_vendor")
    public String AddNewVendorForm(Model model)
    {
        Vendor vendor = new Vendor();
        model.addAttribute("vendor", vendor);
        return "add_new_vendor";
    }

    @PostMapping(value = "save_vendor")
    public String SaveVendor(@ModelAttribute(name = "vendor") Vendor vendor){
        vendorService.saveVendor(vendor);
        return"redirect:/show_all_vendors";
    }

    @GetMapping(value = "edit_vendor/{vendorID}")
    public String EditVendorForm(Model model, @PathVariable(name = "vendorID")Long vendorID)
    {
        Vendor vendor = vendorService.findVendorByID(vendorID);
        model.addAttribute("vendor", vendor);
        return "edit_vendor";
    }

    @GetMapping(value = "delete_vendor/{vendorID}")
    public String DeleteVendorByID(@PathVariable(name = "vendorID")Long vendorID)
    {
        vendorService.deleteVendorByID(vendorID);
        return"redirect:/show_all_vendors";
    }
}
