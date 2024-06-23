package es.upm.miw.sportscentre.views.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeDto {
  private String title;
  private String body;
  private LocalDateTime dateTime;
}