package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoticeControllerTest {

  @Autowired
  private NoticeController noticeController;

  @Test
  void testFindAllNotices() {
    List<Notice> notices = noticeController.findAll();
    assertNotNull(notices);
    assertFalse(notices.isEmpty());
  }

  @Test
  void testFindById() {
    Notice notice = noticeController.findById("1");
    assertNotNull(notice);
    assertEquals("Notice 1", notice.getTitle()); // Replace with expected title
  }

  @Test
  void testSaveAndDelete() {
    Notice newNotice = Notice.builder()
        .title("Test Notice")
        .body("Test Body")
        .dateTime(LocalDateTime.now())
        .build();

    // Save
    Notice savedNotice = noticeController.save(newNotice);
    assertNotNull(savedNotice);
    assertEquals("Test Notice", savedNotice.getTitle());

    // Delete
    noticeController.deleteById(savedNotice.getId());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      noticeController.findById(savedNotice.getId());
    });
    assertEquals("Notice not found", exception.getMessage());
  }

  @Test
  void testUpdateNotice() {
    // Assume "1" is a valid ID
    Notice originalNotice = noticeController.findById("1");
    assertNotNull(originalNotice);

    // Save original state
    Notice aux = Notice.builder()
        .id(originalNotice.getId())
        .title(originalNotice.getTitle())
        .body(originalNotice.getBody())
        .dateTime(originalNotice.getDateTime())
        .build();

    // Update
    originalNotice.setTitle("Updated Notice");
    originalNotice.setBody("Updated Body");

    Notice updatedNotice = noticeController.update("1", originalNotice);
    assertNotNull(updatedNotice);
    assertEquals("Updated Notice", updatedNotice.getTitle());
    assertEquals("Updated Body", updatedNotice.getBody());

    // Restore original state
    noticeController.update("1", aux);

    // Verify original state
    Notice restoredNotice = noticeController.findById("1");
    assertNotNull(restoredNotice);
    assertEquals("Notice 1", restoredNotice.getTitle()); // Expected original title
    assertEquals("Notice body 1", restoredNotice.getBody()); // Expected original body
  }
}
