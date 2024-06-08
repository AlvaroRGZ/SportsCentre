package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "complaints")
public class Complaint {
  @Id
  private String id;
  private String title;
  private String body;
  private LocalDateTime datetime;
}
