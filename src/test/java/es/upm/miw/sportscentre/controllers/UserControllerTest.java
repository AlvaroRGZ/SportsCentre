package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

  @Autowired
  private UserController userController;

  @Autowired
  private ComplaintController complaintController;

  @Test
  void testFindAllUsers() {
    List<User> users = userController.findAll();
    assertNotNull(users);
    assertFalse(users.isEmpty());
  }

  @Test
  void testFindById() {
    User user = userController.findById("1");
    assertNotNull(user);
    assertEquals("admin@gmail.com", user.getEmail());
  }

  @Test
  void testSaveAndDelete() {
    User newUser = User.builder()
        .email("newuser@gmail.com")
        .password("password")
        .role("CLIENT")
        .build();

    // Save
    User savedUser = userController.save(newUser);
    assertNotNull(savedUser);
    assertEquals("newuser@gmail.com", savedUser.getEmail());

    // Delete
    userController.deleteById(savedUser.getId());
    assertThrows(RuntimeException.class, () -> userController.findById(savedUser.getId()));

    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.findById("nonexistent_id");
    });
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void testAddComplaintToUser() {
    Complaint complaint = Complaint.builder()
        .title("New Complaint")
        .body("Complaint body")
        .datetime(LocalDateTime.now())
        .build();

    User user = userController.addComplaintToUser("admin@gmail.com", complaint);
    assertNotNull(user);
    assertTrue(user.getComplaints().contains(complaint));
  }

  @Test
  void testDeleteComplaintFromUser() {
    Complaint complaint = Complaint.builder()
        .title("New Complaint")
        .body("Complaint body")
        .datetime(LocalDateTime.now())
        .build();

    userController.addComplaintToUser("admin@gmail.com", complaint);
    User user = userController.deleteComplaintFromUser("admin@gmail.com", complaint.getId());
    assertNotNull(user);
    assertFalse(user.getComplaints().contains(complaint));
  }

  @Test
  void testFindByEmail() {
    User user = userController.findByEmail("admin@gmail.com");
    assertNotNull(user);
    assertEquals("admin@gmail.com", user.getEmail());
  }

  @Test
  void testExistsByEmail() {
    assertTrue(userController.existsByEmail("admin@gmail.com"));
    assertFalse(userController.existsByEmail("nonexistent@gmail.com"));
  }

  @Test
  void testGetUserComplaints() {
    List<Complaint> complaints = userController.getUserComplaints("a@gmail.com");
    assertNotNull(complaints);
    assertFalse(complaints.isEmpty());
  }

  @Test
  void testFindUsersWhoHaveComplaints() {
    List<User> users = userController.findUsersWhoHaveComplaints();
    assertNotNull(users);
    assertFalse(users.isEmpty());
  }

  // Casos en los que se espera un error
  @Test
  void testFindByIdNotFound() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.findById("nonexistent_id");
    });
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void testGetUserComplaintsNotFound() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.getUserComplaints("nonexistent@gmail.com");
    });
    assertEquals("User with email nonexistent@gmail.com not found", exception.getMessage());
  }

  @Test
  void testAddComplaintToNonexistentUser() {
    Complaint complaint = Complaint.builder()
        .title("New Complaint")
        .body("Complaint body")
        .datetime(LocalDateTime.now())
        .build();

    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.addComplaintToUser("nonexistent@gmail.com", complaint);
    });
    assertEquals("User with email nonexistent@gmail.com not found", exception.getMessage());
  }

  @Test
  void testDeleteComplaintFromNonexistentUser() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.deleteComplaintFromUser("nonexistent@gmail.com", "nonexistent_complaint_id");
    });
    assertEquals("User with email nonexistent@gmail.com not found", exception.getMessage());
  }
}
