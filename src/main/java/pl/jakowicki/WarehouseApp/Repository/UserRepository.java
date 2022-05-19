package pl.jakowicki.WarehouseApp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.jakowicki.WarehouseApp.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllUsersByRoles_Name(String userRole);

    void deleteByEmail(String email);

    User findUserByEmail(String email);

    @Query(value = "SELECT * FROM `user`",
            nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT * FROM `user`WHERE id = ?1",
            nativeQuery = true)
    User findUserById(Long userId);
}