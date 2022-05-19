package pl.jakowicki.WarehouseApp.Service;

import org.springframework.stereotype.Service;
import pl.jakowicki.WarehouseApp.Model.Vendor;
import pl.jakowicki.WarehouseApp.Repository.VendorsRepository;

import java.util.List;

@Service
public class VendorService {

    private VendorsRepository vendorsRepository;

    public VendorService(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    public List<Vendor> findAllVendors() {
        return vendorsRepository.findAll();
    }

    public void saveVendor(Vendor vendor) {
        vendorsRepository.save(vendor);
    }

    public Vendor findVendorByID(Long vendorID) {
        return vendorsRepository.getById(vendorID);
    }

    public void deleteVendorByID(Long vendorID) {
        vendorsRepository.deleteById(vendorID);
    }
}
