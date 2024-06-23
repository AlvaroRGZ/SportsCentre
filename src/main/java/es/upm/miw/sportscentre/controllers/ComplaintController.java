package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.daos.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComplaintController {

  @Autowired
  private ComplaintRepository ComplaintRepository;

  public List<Complaint> findAll() {
    return ComplaintRepository.findAll();
  }

  public Complaint findById(String id) {
    return ComplaintRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Complaint not found"));
  }

  public Complaint save(Complaint complaint) {
    complaint.setId(UUID.randomUUID().toString());
    return ComplaintRepository.save(complaint);
  }

  public void deleteById(String id) {
    ComplaintRepository.deleteById(id);
  }

  public void deleteAllNoticesBeforeDate() {
    ComplaintRepository.deleteAll();
  }
}
