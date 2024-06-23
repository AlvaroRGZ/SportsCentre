package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "materials")
public class Material {
  @Id
  private String id;
  // @Indexed(unique = true)
  private String name;
  private String description;
  private Integer quantity;

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  public void removeQuantity(int quantity) {
    if (this.quantity > quantity) {
      this.quantity -= quantity;
    } else {
      this.quantity = 0;
    }
  }
}
