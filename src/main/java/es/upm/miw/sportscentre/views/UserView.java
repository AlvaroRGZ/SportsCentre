package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.UserController;
import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserView {

  @Autowired
  private UserController userController;

  @GetMapping
  public List<User> getAll() {
    return userController.findAll();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable String id) {
    return userController.findById(id);
  }

  @GetMapping("complaints")
  public List<User> getUsersWhoHaveComplaints() {
    return userController.findUsersWhoHaveComplaints();
  }

  @GetMapping("/{email}/complaints")
  public List<Complaint> findUserComplaints(@PathVariable String email) {
    return userController.getUserComplaints(email);
  }

  @GetMapping("existsByEmail")
  public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
    boolean exists = userController.existsByEmail(email);
    System.out.println("Email " + email + " : " + exists);
    return ResponseEntity.ok(exists);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userController.save(user);
  }

  @PostMapping("/{email}/complaints")
  public User addComplaintToUser(@PathVariable String email, @RequestBody Complaint complaint) {
    return userController.addComplaintToUser(email, complaint);
  }

  @DeleteMapping("/{email}/complaints/{complaintId}")
  public User deleteComplaintFromUser(@PathVariable String email, @PathVariable String complaintId) {
    return userController.deleteComplaintFromUser(email, complaintId);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    userController.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllUsers() {
    userController.deleteAll();
  }
}