package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "sportclasses")
public class SportClass {
  @Id
  private String id;
  private String title;
  private Integer places;
  @DBRef
  private List<User> assistants;
  @DBRef
  private Installation installation;
}