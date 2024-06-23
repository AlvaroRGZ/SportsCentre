package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Complaint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComplaintControllerTest {

  @Autowired
  private ComplaintController complaintController;

  @Test
  void testFindAllComplaints() {
    List<Complaint> complaints = complaintController.findAll();
    assertNotNull(complaints);
    assertFalse(complaints.isEmpty());
    assertEquals("Unscheduled Maintenance", complaints.get(0).getTitle());
  }

  @Test
  void testFindById() {
    Complaint complaint = complaintController.findById("1");
    assertNotNull(complaint);
    assertEquals("Unscheduled Maintenance", complaint.getTitle());
  }

  @Test
  void testSaveAndDelete() {
    Complaint newComplaint = new Complaint();
    newComplaint.setTitle("Test Complaint");
    newComplaint.setBody("Test Body");
    newComplaint.setDatetime(LocalDateTime.now());

    // Save
    Complaint savedComplaint = complaintController.save(newComplaint);
    assertNotNull(savedComplaint);
    assertEquals("Test Complaint", savedComplaint.getTitle());

    // Delete
    complaintController.deleteById(savedComplaint.getId());
    assertThrows(RuntimeException.class, () -> complaintController.findById(savedComplaint.getId()));
  }
}
