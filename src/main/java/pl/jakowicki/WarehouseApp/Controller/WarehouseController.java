package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jakowicki.WarehouseApp.Model.User;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Service.UserService;
import pl.jakowicki.WarehouseApp.Service.WarehouseService;

import java.util.List;

@Controller
public class WarehouseController {

    private WarehouseService warehouseService;
    private UserService userService;

    public WarehouseController(WarehouseService warehouseService, UserService userService) {
        this.warehouseService = warehouseService;
        this.userService = userService;
    }

    @GetMapping(value="usersList")
    public String getUsersList(Model model)
    {
        List<User> userList = userService.showAllUsersList();
        model.addAttribute("userList", userList);
        return "/show_list_of_all_users";
    }

    @GetMapping(value = "/users_warehouse_list/{userId}")
    public String showUsersWarehouseList(Model model, @PathVariable(value = "userId") Long userId)
    {
        User user = userService.findUserById(userId);
        List<Warehouse> warehouseList = user.getWarehouses();
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("user", user);
        return "/show_users_warehouses";
    }

    @GetMapping(value = "/add_user_to_warehouse/{userId}")
    public String showAddUserToWarehouseForm(Model model,  @PathVariable(value = "userId") Long userId)
    {
        User user = userService.findUserById(userId);
        List<Warehouse> warehouseList = warehouseService.getWarehousesListThatUserCanByAddedTo(user);
        model.addAttribute("warehouseList", warehouseList);
        return "/add_user_to_warehouse";
    }
}
