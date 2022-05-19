package pl.jakowicki.WarehouseApp.DTO;


import pl.jakowicki.WarehouseApp.Model.User;
import pl.jakowicki.WarehouseApp.Model.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    public static UserCredentialsDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email, password, roles);
    }
}