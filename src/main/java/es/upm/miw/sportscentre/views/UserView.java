package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.UserController;
import es.upm.miw.sportscentre.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserView {

  @Autowired
  private UserController UserController;

  @GetMapping
  public List<User> getAll() {
    return UserController.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable String id) {
    return UserController.findById(id);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return UserController.save(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    UserController.deleteById(id);
  }
}