package es.upm.miw.sportscentre.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NoticeTest {

  @Test
  void testBuilderAndGetters() {
    LocalDateTime now = LocalDateTime.now();
    Notice notice = Notice.builder()
        .id("1")
        .title("Test Notice")
        .body("This is a test notice.")
        .dateTime(now)
        .build();

    assertEquals("1", notice.getId());
    assertEquals("Test Notice", notice.getTitle());
    assertEquals("This is a test notice.", notice.getBody());
    assertEquals(now, notice.getDateTime());
  }

  @Test
  void testSetters() {
    LocalDateTime now = LocalDateTime.now();
    Notice notice = new Notice();
    notice.setId("1");
    notice.setTitle("Test Notice");
    notice.setBody("This is a test notice.");
    notice.setDateTime(now);

    assertEquals("1", notice.getId());
    assertEquals("Test Notice", notice.getTitle());
    assertEquals("This is a test notice.", notice.getBody());
    assertEquals(now, notice.getDateTime());
  }

  @Test
  void testNoArgsConstructor() {
    Notice notice = new Notice();
    assertNull(notice.getId());
    assertNull(notice.getTitle());
    assertNull(notice.getBody());
    assertNull(notice.getDateTime());
  }

  @Test
  void testAllArgsConstructor() {
    LocalDateTime now = LocalDateTime.now();
    Notice notice = new Notice("1", "Test Notice", "This is a test notice.", now);

    assertEquals("1", notice.getId());
    assertEquals("Test Notice", notice.getTitle());
    assertEquals("This is a test notice.", notice.getBody());
    assertEquals(now, notice.getDateTime());
  }
}
