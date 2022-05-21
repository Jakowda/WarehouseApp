package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.jakowicki.WarehouseApp.Model.User;
import pl.jakowicki.WarehouseApp.Model.UserToWarehouse;
import pl.jakowicki.WarehouseApp.Model.Warehouse;
import pl.jakowicki.WarehouseApp.Service.UserService;
import pl.jakowicki.WarehouseApp.Service.UserToWarehouseService;
import pl.jakowicki.WarehouseApp.Service.WarehouseService;

import java.util.List;

@Controller
public class WarehouseController {

    private WarehouseService warehouseService;
    private UserService userService;
    private UserToWarehouseService userToWarehouseService;

    public WarehouseController(WarehouseService warehouseService, UserService userService, UserToWarehouseService userToWarehouseService) {
        this.warehouseService = warehouseService;
        this.userService = userService;
        this.userToWarehouseService = userToWarehouseService;
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
        List<Warehouse> warehouseList = warehouseService.findAllWarehouses();
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("user", user);
        return "/add_user_to_warehouse";
    }

    @GetMapping(value = "/add_to_warehouse/{warehouseId}/user/{userId}")
    public String addUserToWarehouse(@PathVariable(value = "warehouseId") Long warehouseId, @PathVariable(value = "userId") Long userId )
    {
        UserToWarehouse userToWarehouse = userToWarehouseService.ifNullThenAddNewRelation(warehouseId, userId);
        if(userToWarehouse == null)
        {
            UserToWarehouse userToWarehouseObject = new UserToWarehouse(userId, warehouseId);
            userToWarehouseService.saveNewRelation(userToWarehouseObject);
        }
        return "redirect:/users_warehouse_list/"+userId;
    }
}
