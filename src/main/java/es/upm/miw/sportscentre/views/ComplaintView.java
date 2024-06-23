package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.ComplaintController;
import es.upm.miw.sportscentre.models.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintView {

  private ComplaintController complaintController;
  ComplaintView(@Autowired ComplaintController complaintController) {
    this.complaintController = complaintController;
  }
  @GetMapping
  public List<Complaint> getAll() {
    return complaintController.findAll();
  }

  @GetMapping("/{id}")
  public Complaint getComplaintById(@PathVariable String id) {
    return complaintController.findById(id);
  }

  @PostMapping
  public Complaint createComplaint(@RequestBody Complaint complaint) {
    return complaintController.save(complaint);
  }

  @DeleteMapping("/{id}")
  public void deleteComplaint(@PathVariable String id) {
    complaintController.deleteById(id);
  }

  @DeleteMapping("/all")
  public void deleteAllComplaintsBeforeDate() {
    complaintController.deleteAllNoticesBeforeDate();
  }
}