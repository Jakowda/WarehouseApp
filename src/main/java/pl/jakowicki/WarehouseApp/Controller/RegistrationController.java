package pl.jakowicki.WarehouseApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jakowicki.WarehouseApp.Model.UserRegistrationDto;
import pl.jakowicki.WarehouseApp.Service.UserService;

@Controller
class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    String registrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        System.out.println("register form");
        return "registration-form";
    }

    @PostMapping(value ="/register")
    String register(@ModelAttribute(name = "user")UserRegistrationDto userRegistrationDto) {
        System.out.println("przeslano formularz");
        userService.register(userRegistrationDto);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    String registrationConfirmation() {
        return "registration-confirmation";
    }
}