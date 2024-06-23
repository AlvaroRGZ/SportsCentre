package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.User;
import es.upm.miw.sportscentre.models.daos.ComplaintRepository;
import es.upm.miw.sportscentre.models.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ComplaintController complaintController;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public User save(User user) {
    if (!userRepository.existsByEmail(user.getEmail())) {
      return userRepository.save(
          User.builder()
              .name(user.getEmail().substring(0, user.getEmail().indexOf('@')))
              .email(user.getEmail())
              .password(user.getPassword())
              .role(user.getRole())
              .complaints(List.of())
              .build());
    }
    return user;
  }

  public void deleteAll() {
    this.userRepository.deleteAll();
  }

  public User findByEmail(String email) {
    return this.userRepository.findUserByEmail(email)
        .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
  }

  public boolean existsByEmail(String email) {
    return this.userRepository.existsByEmail(email);
  }

  public User addComplaintToUser(String email, Complaint complaint) {
    User user = this.findByEmail(email);
    this.complaintController.save(complaint);
    user.addComplaint(complaint);
    return this.userRepository.save(user);
  }

  public void deleteById(String id) {
    this.userRepository.deleteById(id);
  }

  public User deleteComplaintFromUser(String email, String complaintId) {
    User user = this.findByEmail(email);
    Complaint complaint = this.complaintController.findById(complaintId);
    user.deleteComplaint(complaint);
    this.complaintController.deleteById(complaintId);
    return this.userRepository.save(user);
  }

  public List<Complaint> getUserComplaints(String email) {
    User user = this.findByEmail(email);
    return user.getComplaints();
  }

  public List<User> findUsersWhoHaveComplaints() {
    return this.userRepository.findUsersWithNonEmptyComplaints();
  }
}