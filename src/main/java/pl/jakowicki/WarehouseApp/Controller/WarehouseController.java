package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping(value="warehouseList")
    public String showWarehouseList(Model model, Authentication authentication)
    {
        User user = userService.findUserByEmail(authentication.getName());
        List<Warehouse> warehouseList = user.getWarehouses();
        model.addAttribute("warehouseList", warehouseList);
        System.out.println(warehouseList);
        return "show_warehouses";
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

    @GetMapping(value = "/remove_from_warehouse/{warehouseId}/user/{userId}")
    public String deleteUserWarehouseConnection(@PathVariable(value = "warehouseId") Long warehouseId, @PathVariable(value = "userId") Long userId)
    {
        UserToWarehouse userToWarehouse = userToWarehouseService.findRelationByWarehouseAndUserId(warehouseId,userId );
        userToWarehouseService.deleteUserWarehouseConnection(userToWarehouse);
        return "redirect:/users_warehouse_list/"+userId;
    }

    @GetMapping(value = "/edit_warehouse/{warehouseId}")
    public String editWarehouseName(Model model, @PathVariable(value = "warehouseId") Long warehouseId)
    {
        Warehouse warehouse = warehouseService.
    }

}
