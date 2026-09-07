package devcaio.ecommerce.controller;

import devcaio.ecommerce.dto.CreateUserDto;
import devcaio.ecommerce.entity.UserEntity;
import devcaio.ecommerce.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {

        var user = userService.createUser(dto);
            return ResponseEntity.created(URI.create("/users" + user.getUserId())).build();


    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> findbyId(@PathVariable ("userId") UUID userId) {

        var user = userService.findById(userId);

        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletebyId(@PathVariable ("userId") UUID userId) {

        var deleted = userService.deleteById(userId);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

    

}
