package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

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
  private String role; // CLIENT, TEACHER, ADMIN
  @DBRef
  private List<Complaint> complaints;
}
