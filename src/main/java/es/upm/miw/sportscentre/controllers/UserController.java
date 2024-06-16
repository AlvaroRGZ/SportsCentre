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
    return userRepository.findById(id).orElse(null);
  }

  public User save(User user) {
    System.out.println("GUARDANDO USUARIO " + user.getEmail());

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

  public void deleteById(String id) {
    //this.findById(id).getComplaints().forEach(complaint -> {
    //  this.complaintRepository.delete(complaint);
    //});
    userRepository.deleteById(id);
  }

  public void deleteAll() {
    this.userRepository.deleteAll();
  }

  public User findByEmail(String email) {
    return this.userRepository.findUserByEmail(email);
  }

  public boolean existsByEmail(String email) {
    return this.userRepository.existsByEmail(email);
  }

  public User addComplaintToUser(String email, Complaint complaint) {
    User user = this.userRepository.findUserByEmail(email);
    this.complaintController.save(complaint);
    user.addComplaint(complaint);
    System.out.println("USER: " + user);
    System.out.println("COMPLAINT: " + complaint);
    return this.userRepository.save(user);
  }

  public User deleteComplaintFromUser(String email, String complaintId) {
    User user = this.userRepository.findUserByEmail(email);
    Complaint complaint = this.complaintController.findById(complaintId);
    user.deleteComplaint(complaint);
    this.complaintController.deleteById(complaintId);
    return this.userRepository.save(user);
  }

  public List<Complaint> getUserComplaints(String email) {
    User user = userRepository.findUserByEmail(email);
    if (user != null) {
      return user.getComplaints();
    }
    // Handle case when user is not found
    throw new IllegalArgumentException("User with email " + email + " not found");
  }
}