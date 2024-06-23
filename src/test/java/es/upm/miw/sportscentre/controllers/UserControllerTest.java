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
    assertEquals("admin", users.get(0).getName());
  }

  @Test
  void testFindById() {
    User user = userController.findById("1");
    assertNotNull(user);
    assertEquals("admin", user.getName());
  }

  @Test
  void testSaveAndDelete() {
    User newUser = User.builder()
        .name("eve")
        .email("eve@example.com")
        .password("password5")
        .role("CLIENT")
        .complaints(List.of())
        .build();

    // Save
    User savedUser = userController.save(newUser);
    assertNotNull(savedUser);
    assertEquals("eve", savedUser.getName());

    // Delete
    userController.deleteById(savedUser.getId());
    assertNull(userController.findById(savedUser.getId()));
  }

  @Test
  void testAddComplaintToUser() {
    Complaint newComplaint = Complaint.builder()
        .title("New Complaint")
        .body("New complaint body")
        .datetime(LocalDateTime.now())
        .build();

    User user = userController.addComplaintToUser("admin@gmail.com", newComplaint);
    assertNotNull(user);
    assertTrue(user.getComplaints().stream().anyMatch(complaint -> "New Complaint".equals(complaint.getTitle())));
  }

  @Test
  void testDeleteComplaintFromUser() {
    User user = userController.findByEmail("client@gmail.com");
    assertNotNull(user);
    assertFalse(user.getComplaints().isEmpty());

    Complaint complaint = user.getComplaints().get(0);
    user = userController.deleteComplaintFromUser(user.getEmail(), complaint.getId());
    assertNotNull(user);
    assertFalse(user.getComplaints().stream().anyMatch(c -> c.getId().equals(complaint.getId())));
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
}
