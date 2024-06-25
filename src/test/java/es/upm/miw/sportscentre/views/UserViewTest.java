package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.UserController;
import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserController userController;

  @Test
  void testGetAllUsers() {
    ResponseEntity<User[]> response = restTemplate.getForEntity("/users", User[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User[] users = response.getBody();
    assertNotNull(users);
    assertTrue(users.length > 0);
  }

  @Test
  void testGetUserById() {
    ResponseEntity<User> response = restTemplate.getForEntity("/users/1", User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User user = response.getBody();
    assertNotNull(user);
    assertEquals("admin@gmail.com", user.getEmail());
  }

  @Test
  void testGetUsersWhoHaveComplaints() {
    ResponseEntity<User[]> response = restTemplate.getForEntity("/users/complaints", User[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User[] users = response.getBody();
    assertNotNull(users);
    assertTrue(users.length > 0);
  }

  @Test
  void testFindUserComplaints() {
    ResponseEntity<Complaint[]> response = restTemplate.getForEntity("/users/a@gmail.com/complaints", Complaint[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Complaint[] complaints = response.getBody();
    assertNotNull(complaints);
    assertTrue(complaints.length > 0);
  }

  @Test
  void testExistsByEmail() {
    ResponseEntity<Boolean> response = restTemplate.getForEntity("/users/existsByEmail?email=admin@gmail.com", Boolean.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Boolean exists = response.getBody();
    assertTrue(exists);
  }

  @Test
  void testCreateUser() {
    User newUser = User.builder()
        .email("testuser@gmail.com")
        .password("password")
        .role("CLIENT")
        .build();
    ResponseEntity<User> response = restTemplate.postForEntity("/users", newUser, User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User savedUser = response.getBody();
    assertNotNull(savedUser);
    assertEquals("testuser@gmail.com", savedUser.getEmail());
  }

  @Test
  void testAddComplaintToUser() {
    Complaint newComplaint = Complaint.builder()
        .title("Test Complaint")
        .body("Test Body")
        .build();
    ResponseEntity<User> response = restTemplate.postForEntity("/users/a@gmail.com/complaints", newComplaint, User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User updatedUser = response.getBody();
    assertNotNull(updatedUser);
    assertTrue(updatedUser.getComplaints().stream().anyMatch(c -> c.getTitle().equals("Test Complaint")));
  }

  @Test
  void testDeleteComplaintFromUser() {
    // Adding a new complaint to delete
    Complaint newComplaint = Complaint.builder()
        .title("Complaint to Delete")
        .body("This complaint will be deleted")
        .build();
    User user = userController.addComplaintToUser("a@gmail.com", newComplaint);

    // Deleting the newly added complaint
    String complaintId = user.getComplaints().stream()
        .filter(c -> c.getTitle().equals("Complaint to Delete"))
        .findFirst()
        .orElseThrow()
        .getId();
    restTemplate.delete("/users/a@gmail.com/complaints/" + complaintId);

    // Verify the complaint is deleted
    User updatedUser = userController.findByEmail("a@gmail.com");
    assertTrue(updatedUser.getComplaints().stream().noneMatch(c -> c.getId().equals(complaintId)));
  }

  @Test
  void testDeleteUser() {
    User newUser = User.builder()
        .email("usertodelete@gmail.com")
        .password("password")
        .role("CLIENT")
        .build();
    User savedUser = userController.save(newUser);

    restTemplate.delete("/users/" + savedUser.getId());

    Exception exception = assertThrows(RuntimeException.class, () -> {
      userController.findById(savedUser.getId());
    });
    assertEquals("User not found", exception.getMessage());
  }
}
