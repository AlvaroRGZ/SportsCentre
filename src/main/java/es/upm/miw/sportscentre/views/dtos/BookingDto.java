package es.upm.miw.sportscentre.views.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDto {
  private LocalDateTime datetime;
  private LocalDateTime registrationTime;
  private String booker;
  private String installation;
  private List<String> materials;
}
