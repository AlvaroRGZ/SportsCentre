package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "installations")
public class Installation {
  @Id
  private String id;
  private String name;
  private String description;
  private Integer capacity;
  private BigDecimal rentalPrice;
}