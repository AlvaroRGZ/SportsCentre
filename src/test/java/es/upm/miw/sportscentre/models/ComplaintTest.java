package es.upm.miw.sportscentre.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplaintTest {
  @Test
  public void testComplaintBuilderAndGetters() {
    String title = "Late Opening";
    String body = "The facility opened 30 minutes late.";
    LocalDateTime datetime = LocalDateTime.now().minusDays(1);

    Complaint complaint = Complaint.builder()
        .title(title)
        .body(body)
        .datetime(datetime)
        .build();

    assertThat(complaint.getTitle()).isEqualTo(title);
    assertThat(complaint.getBody()).isEqualTo(body);
    assertThat(complaint.getDatetime()).isEqualTo(datetime);
  }

  @Test
  public void testComplaintSetters() {
    Complaint complaint = new Complaint();
    String title = "Unattended Reception";
    String body = "No one was present at the reception for an extended period.";
    LocalDateTime datetime = LocalDateTime.now().minusHours(2);

    complaint.setTitle(title);
    complaint.setBody(body);
    complaint.setDatetime(datetime);

    assertThat(complaint.getTitle()).isEqualTo(title);
    assertThat(complaint.getBody()).isEqualTo(body);
    assertThat(complaint.getDatetime()).isEqualTo(datetime);
  }

  @Test
  public void testNoArgsConstructor() {
    Complaint complaint = new Complaint();

    assertThat(complaint.getId()).isNull();
    assertThat(complaint.getTitle()).isNull();
    assertThat(complaint.getBody()).isNull();
    assertThat(complaint.getDatetime()).isNull();
  }

  @Test
  public void testAllArgsConstructor() {
    String id = "3";
    String title = "Noise Complaint";
    String body = "Excessive noise during quiet hours.";
    LocalDateTime datetime = LocalDateTime.now().minusDays(2);

    Complaint complaint = new Complaint(id, title, body, datetime);

    assertThat(complaint.getId()).isEqualTo(id);
    assertThat(complaint.getTitle()).isEqualTo(title);
    assertThat(complaint.getBody()).isEqualTo(body);
    assertThat(complaint.getDatetime()).isEqualTo(datetime);
  }
}
