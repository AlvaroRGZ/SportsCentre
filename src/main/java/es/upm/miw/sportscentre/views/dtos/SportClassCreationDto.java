package es.upm.miw.sportscentre.views.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SportClassCreationDto {
  private String title;
  private String installation;
  private Integer places;

}
