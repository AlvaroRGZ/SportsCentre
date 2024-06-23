package es.upm.miw.sportscentre.models;
import es.upm.miw.sportscentre.views.dtos.NoticeDto;
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
@Document
public class Notice {
  @Id
  private String id;
  private String title;
  private String body;
  private LocalDateTime dateTime;

  static public Notice fromNoticeDto(NoticeDto notice) {
    return Notice.builder()
        .title(notice.getTitle())
        .body(notice.getBody())
        .dateTime(notice.getDateTime())
        .build();
  }
}
