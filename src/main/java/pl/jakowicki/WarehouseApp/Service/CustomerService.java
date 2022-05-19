package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.Customer;
import pl.jakowicki.WarehouseApp.Repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomerByID(Long customerID) {
        return customerRepository.getById(customerID);
    }

    public void deleteCustomerByID(Long customerID) {
        customerRepository.deleteById(customerID);
    }
}
