package es.upm.miw.sportscentre.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  private String role;
  @DBRef
  private List<Complaint> complaints;

  public void addComplaint(Complaint complaint) {
    this.complaints.add(complaint);
  }

  public void deleteComplaint(Complaint complaint) {
    this.complaints.remove(complaint);
  }
}
