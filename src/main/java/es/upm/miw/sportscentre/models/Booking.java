package es.upm.miw.sportscentre.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bookings")
public class Booking {
  @Id
  private String id;
  private LocalDateTime datetime;
  private LocalDateTime registrationTime;
  @DBRef
  private User booker;
  @DBRef
  private Installation installation;
  @DBRef
  private List<Material> materials;
}
