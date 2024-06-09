package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "materials")
public class Material {
  @Id
  private String id;
  private String name;
  private String description;
  private Integer quantity;
}
