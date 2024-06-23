package es.upm.miw.sportscentre.views.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class BookingDto {
  private LocalDateTime datetime;
  private LocalDateTime registrationTime;
  private String booker;
  private String installation;
  private List<String> materials;
}
