package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.ComplaintController;
import es.upm.miw.sportscentre.models.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintView {

  @Autowired
  private ComplaintController ComplaintController;

  @GetMapping
  public List<Complaint> getAll() {
    return ComplaintController.findAll();
  }

  @GetMapping("/{id}")
  public Complaint getComplaintById(@PathVariable String id) {
    return ComplaintController.findById(id);
  }

  @PostMapping
  public Complaint createComplaint(@RequestBody Complaint Complaint) {
    return ComplaintController.save(Complaint);
  }

  @DeleteMapping("/{id}")
  public void deleteComplaint(@PathVariable String id) {
    ComplaintController.deleteById(id);
  }
}