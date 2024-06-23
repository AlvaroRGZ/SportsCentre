package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.views.dtos.BookingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingControllerTest {

  @Autowired
  private BookingController bookingController;

  @Autowired
  private MaterialController materialController;

  @Test
  void testFindAll() {
    List<Booking> bookings = bookingController.findAll();
    assertNotNull(bookings);
    assertFalse(bookings.isEmpty());
  }

  @Test
  void testFindById() {
    Booking booking = bookingController.findById("1");
    assertNotNull(booking);
    assertEquals("admin@gmail.com", booking.getBooker().getEmail());
  }

  @Test
  void testFindByInstallationId() {
    List<Booking> bookings = bookingController.findByInstallationId("1");
    assertNotNull(bookings);
    assertFalse(bookings.isEmpty());
  }

  @Test
  void testSaveAndDelete() {
    BookingDto newBookingDto = BookingDto.builder()
        .booker("admin@gmail.com")
        .installation("1")
        .datetime(LocalDateTime.now().plusDays(1))
        .registrationTime(LocalDateTime.now())
        .materials(List.of("1", "2"))
        .build();

    // Save
    Booking savedBooking = bookingController.save(newBookingDto);
    assertNotNull(savedBooking);
    assertEquals("admin@gmail.com", savedBooking.getBooker().getEmail());

    // Delete
    bookingController.deleteById(savedBooking.getId());
    assertNull(bookingController.findById(savedBooking.getId()));
  }

  @Test
  void testFindByBookerEmail() {
    List<Booking> bookings = bookingController.findByBookerEmail("admin@gmail.com");
    assertNotNull(bookings);
    assertFalse(bookings.isEmpty());
  }

  @Test
  void testUpdateMaterials() {
    List<Material> materials = materialController.findByListOfIds(List.of("1", "2"));
    Booking updatedBooking = bookingController.updateMaterials("1", materials);
    assertNotNull(updatedBooking);
    assertEquals(2, updatedBooking.getMaterials().size());
  }

  @Test
  void testDeleteById() {
    BookingDto newBookingDto = BookingDto.builder()
        .booker("admin@gmail.com")
        .installation("1")
        .datetime(LocalDateTime.now().plusDays(1))
        .registrationTime(LocalDateTime.now())
        .materials(List.of("1", "2"))
        .build();

    // Save
    Booking savedBooking = bookingController.save(newBookingDto);
    assertNotNull(savedBooking);

    // Delete
    bookingController.deleteById(savedBooking.getId());
    assertNull(bookingController.findById(savedBooking.getId()));
  }

  @Test
  void testSaveBookingWithNonexistentInstallation() {
    BookingDto newBookingDto = BookingDto.builder()
        .booker("admin@gmail.com")
        .installation("nonexistent_installation_id")
        .datetime(LocalDateTime.now().plusDays(1))
        .registrationTime(LocalDateTime.now())
        .materials(List.of("1", "2"))
        .build();

    Exception exception = assertThrows(RuntimeException.class, () -> {
      bookingController.save(newBookingDto);
    });
    assertEquals("Installation not found", exception.getMessage());
  }

  @Test
  void testSaveBookingWithNonexistentBooker() {
    BookingDto newBookingDto = BookingDto.builder()
        .booker("nonexistent@gmail.com")
        .installation("1")
        .datetime(LocalDateTime.now().plusDays(1))
        .registrationTime(LocalDateTime.now())
        .materials(List.of("1", "2"))
        .build();

    Exception exception = assertThrows(RuntimeException.class, () -> {
      bookingController.save(newBookingDto);
    });
    assertEquals("User with email nonexistent@gmail.com not found", exception.getMessage());
  }
}