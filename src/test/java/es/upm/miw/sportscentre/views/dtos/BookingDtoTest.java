package es.upm.miw.sportscentre.views.dtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoTest {

  @Test
  void testBuilderAndGetters() {
    LocalDateTime now = LocalDateTime.now();
    BookingDto bookingDto = BookingDto.builder()
        .datetime(now)
        .registrationTime(now.minusHours(1))
        .booker("booker@example.com")
        .installation("1")
        .materials(List.of("1", "2"))
        .build();

    assertEquals(now, bookingDto.getDatetime());
    assertEquals(now.minusHours(1), bookingDto.getRegistrationTime());
    assertEquals("booker@example.com", bookingDto.getBooker());
    assertEquals("1", bookingDto.getInstallation());
    assertEquals(List.of("1", "2"), bookingDto.getMaterials());
  }

  @Test
  void testSetters() {
    LocalDateTime now = LocalDateTime.now();
    BookingDto bookingDto = new BookingDto();
    bookingDto.setDatetime(now);
    bookingDto.setRegistrationTime(now.minusHours(1));
    bookingDto.setBooker("booker@example.com");
    bookingDto.setInstallation("1");
    bookingDto.setMaterials(List.of("1", "2"));

    assertEquals(now, bookingDto.getDatetime());
    assertEquals(now.minusHours(1), bookingDto.getRegistrationTime());
    assertEquals("booker@example.com", bookingDto.getBooker());
    assertEquals("1", bookingDto.getInstallation());
    assertEquals(List.of("1", "2"), bookingDto.getMaterials());
  }

  @Test
  void testNoArgsConstructor() {
    BookingDto bookingDto = new BookingDto();
    assertNull(bookingDto.getDatetime());
    assertNull(bookingDto.getRegistrationTime());
    assertNull(bookingDto.getBooker());
    assertNull(bookingDto.getInstallation());
    assertNull(bookingDto.getMaterials());
  }

  @Test
  void testAllArgsConstructor() {
    LocalDateTime now = LocalDateTime.now();
    BookingDto bookingDto = new BookingDto(now, now.minusHours(1), "booker@example.com", "1", List.of("1", "2"));

    assertEquals(now, bookingDto.getDatetime());
    assertEquals(now.minusHours(1), bookingDto.getRegistrationTime());
    assertEquals("booker@example.com", bookingDto.getBooker());
    assertEquals("1", bookingDto.getInstallation());
    assertEquals(List.of("1", "2"), bookingDto.getMaterials());
  }
}