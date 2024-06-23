package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.NoticeController;
import es.upm.miw.sportscentre.models.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticeViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private NoticeController noticeController;

  @Test
  void testGetAllNotices() {
    ResponseEntity<Notice[]> response = restTemplate.getForEntity("/notices", Notice[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Notice[] notices = response.getBody();
    assertNotNull(notices);
    assertTrue(notices.length > 0);
  }

  @Test
  void testGetNoticeById() {
    ResponseEntity<Notice> response = restTemplate.getForEntity("/notices/1", Notice.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Notice notice = response.getBody();
    assertNotNull(notice);
    assertEquals("Notice 1", notice.getTitle());
  }

  @Test
  void testCreateNotice() {
    Notice newNotice = Notice.builder()
        .title("Test Notice")
        .body("Test Body")
        .dateTime(LocalDateTime.now())
        .build();
    ResponseEntity<Notice> response = restTemplate.postForEntity("/notices", newNotice, Notice.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Notice savedNotice = response.getBody();
    assertNotNull(savedNotice);
    assertEquals("Test Notice", savedNotice.getTitle());
  }

  @Test
  void testUpdateNotice() {
    Notice originalNotice = noticeController.findById("1");
    Notice backupNotice = new Notice(originalNotice.getId(), originalNotice.getTitle(), originalNotice.getBody(), originalNotice.getDateTime());

    originalNotice.setTitle("Updated Notice");
    originalNotice.setBody("Updated Body");

    restTemplate.put("/notices/1", originalNotice);

    Notice updatedNotice = noticeController.findById("1");
    assertNotNull(updatedNotice);
    assertEquals("Updated Notice", updatedNotice.getTitle());
    assertEquals("Updated Body", updatedNotice.getBody());

    // Restore original notice
    restTemplate.put("/notices/1", backupNotice);
    Notice restoredNotice = noticeController.findById("1");
    assertNotNull(restoredNotice);
    assertEquals(backupNotice.getTitle(), restoredNotice.getTitle());
    assertEquals(backupNotice.getBody(), restoredNotice.getBody());
  }

  @Test
  void testDeleteNotice() {
    Notice newNotice = Notice.builder()
        .title("Notice to Delete")
        .body("This notice will be deleted")
        .dateTime(LocalDateTime.now())
        .build();
    Notice savedNotice = noticeController.save(newNotice);

    restTemplate.delete("/notices/" + savedNotice.getId());

    Exception exception = assertThrows(RuntimeException.class, () -> {
      noticeController.findById(savedNotice.getId());
    });
    assertEquals("Notice not found", exception.getMessage());
  }

}
