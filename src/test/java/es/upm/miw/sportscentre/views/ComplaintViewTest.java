package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.ComplaintController;
import es.upm.miw.sportscentre.models.Complaint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "server.port=4201")
public class ComplaintViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ComplaintController complaintController;

  @Test
  void testGetAllComplaints() {
    ResponseEntity<Complaint[]> response = restTemplate.getForEntity("/complaints", Complaint[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Complaint[] complaints = response.getBody();
    assertNotNull(complaints);
    assertTrue(complaints.length > 0);
  }

  @Test
  void testGetComplaintById() {
    ResponseEntity<Complaint> response = restTemplate.getForEntity("/complaints/1", Complaint.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Complaint complaint = response.getBody();
    assertNotNull(complaint);
    assertEquals("Unscheduled Maintenance", complaint.getTitle());
  }

  @Test
  void testCreateComplaint() {
    Complaint newComplaint = Complaint.builder()
        .title("Test Complaint")
        .body("Test Body")
        .datetime(LocalDateTime.now())
        .build();
    ResponseEntity<Complaint> response = restTemplate.postForEntity("/complaints", newComplaint, Complaint.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Complaint savedComplaint = response.getBody();
    assertNotNull(savedComplaint);
    assertEquals("Test Complaint", savedComplaint.getTitle());
  }

  @Test
  void testDeleteComplaint() {
    Complaint newComplaint = Complaint.builder()
        .title("Complaint to Delete")
        .body("This complaint will be deleted")
        .datetime(LocalDateTime.now())
        .build();
    Complaint savedComplaint = complaintController.save(newComplaint);

    restTemplate.delete("/complaints/" + savedComplaint.getId());

    Exception exception = assertThrows(RuntimeException.class, () -> {
      complaintController.findById(savedComplaint.getId());
    });
    assertEquals("Complaint not found", exception.getMessage());
  }
}
