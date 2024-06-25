package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.BookingController;
import es.upm.miw.sportscentre.controllers.InstallationController;
import es.upm.miw.sportscentre.controllers.MaterialController;
import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.views.dtos.BookingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "server.port=4201")
public class BookingViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private BookingController bookingController;
  @Autowired
  private MaterialController materialController;
  @Autowired
  private InstallationController installationController;

  @Test
  void testGetAllBookings() {
    ResponseEntity<Booking[]> response = restTemplate.getForEntity("/bookings", Booking[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Booking[] bookings = response.getBody();
    assertNotNull(bookings);
    assertTrue(bookings.length > 0);
  }

  @Test
  void testGetBookingById() {
    ResponseEntity<Booking> response = restTemplate.getForEntity("/bookings/1", Booking.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Booking booking = response.getBody();
    assertNotNull(booking);
    assertEquals("1", booking.getId());
  }

  @Test
  void testFindByInstallationId() {
    ResponseEntity<Booking[]> response = restTemplate.getForEntity("/bookings/installation/1", Booking[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Booking[] bookings = response.getBody();
    assertNotNull(bookings);
    assertTrue(bookings.length > 0);
  }

  @Test
  void testFindByBookerEmail() {
    ResponseEntity<Booking[]> response = restTemplate.getForEntity("/bookings/booker/admin@gmail.com", Booking[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Booking[] bookings = response.getBody();
    assertNotNull(bookings);
    assertTrue(bookings.length > 0);
  }

  @Test
  void testCreateBooking() {
    BookingDto newBooking = BookingDto.builder()
        .booker("admin@gmail.com")
        .installation("1")
        .datetime(LocalDateTime.now())
        .registrationTime(LocalDateTime.now())
        .materials(List.of(
            this.materialController.findAll().get(1).getId(),
            this.materialController.findAll().get(2).getId()
        ))
        .build();
    ResponseEntity<Booking> response = restTemplate.postForEntity("/bookings", newBooking, Booking.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Booking savedBooking = response.getBody();
    assertNotNull(savedBooking);
    assertEquals("admin@gmail.com", savedBooking.getBooker().getEmail());
  }

  @Test
  void testUpdateBookingMaterials() {
    Booking originalBooking = bookingController.findById("1");
    List<Material> originalMaterials = originalBooking.getMaterials();

    // Update
    List<Material> updatedMaterials = List.of(
        materialController.findAll().get(1),
        materialController.findAll().get(2)
    );

    restTemplate.put("/bookings/1/materials", updatedMaterials);

    Booking updatedBooking = bookingController.findById("1");
    assertNotNull(updatedBooking);
    assertEquals(updatedMaterials.size(), updatedBooking.getMaterials().size());
    assertEquals(updatedMaterials.get(0).getName(), updatedBooking.getMaterials().get(0).getName());

    // Restore original materials
    restTemplate.put("/bookings/1/materials", originalMaterials);
    Booking restoredBooking = bookingController.findById("1");
    assertNotNull(restoredBooking);
    assertEquals(originalMaterials.size(), restoredBooking.getMaterials().size());
    assertEquals(originalMaterials.get(0).getName(), restoredBooking.getMaterials().get(0).getName());
  }



  @Test
  void testDeleteBooking() {
    BookingDto newBooking = BookingDto.builder()
        .booker("admin@gmail.com")
        .installation("1")
        .datetime(LocalDateTime.now())
        .registrationTime(LocalDateTime.now())
        .materials(List.of(
            this.materialController.findAll().get(1).getId(),
            this.materialController.findAll().get(2).getId()
        ))
        .build();
    Booking savedBooking = bookingController.save(newBooking);
    restTemplate.delete("/bookings/" + savedBooking.getId());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      bookingController.findById(savedBooking.getId());
    });
    assertEquals("Booking not found", exception.getMessage());
  }
}
