package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakowicki.WarehouseApp.Model.Customer;
import pl.jakowicki.WarehouseApp.Service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/show_all_customers")
    public String ShowClientList(Model model)
    {
        List<Customer> custometList = customerService.findAllCustomers();
        model.addAttribute("customerList", custometList);
        return "/show_list_of_customers";
    }

    @GetMapping(value = "/add_customer")
    public String ShowAddNewClientForm(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "/add_new_customer";
    }

    @PostMapping(value="/save_customer")
    public String SaveNewClient(@ModelAttribute(name = "customer") Customer customer)
    {
        customerService.saveCustomer(customer);
        return "redirect:/show_all_customers";
    }

    @GetMapping(value = "/edit_customer/{customerID}")
    public String ShowClientEditForm(@PathVariable(name = "customerID")Long customerID, Model model)
    {
        Customer customer = customerService.findCustomerByID(customerID);
        model.addAttribute("customer", customer);
        return "/edit_customer";
    }

    @GetMapping(value = "/delete_client/{customerID}")
    public String DeleteClientByID(@PathVariable(name = "customerID")Long customerID)
    {
        customerService.deleteCustomerByID(customerID);
        return "redirect:/show_all_customers";
    }

    @GetMapping(value = "/test")
    public String test(Model model)
    {
        return"/test";
    }
}
