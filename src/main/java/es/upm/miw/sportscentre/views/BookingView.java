package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.BookingController;
import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.views.dtos.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingView {

  private BookingController bookingController;

  BookingView(@Autowired BookingController bookingController) {
    this.bookingController = bookingController;
  }

  @GetMapping
  public List<Booking> getAll() {
    return bookingController.findAll();
  }

  @GetMapping("/{id}")
  public Booking getBookingById(@PathVariable String id) {
    return bookingController.findById(id);
  }

  @GetMapping("/installation/{installationId}")
  public List<Booking> findByInstallationId(@PathVariable String installationId) {
    return bookingController.findByInstallationId(installationId);
  }

  @GetMapping("/booker/{email}")
  public List<Booking> findByBookerEmail(@PathVariable String email) {
    return bookingController.findByBookerEmail(email);
  }

  @PostMapping
  public Booking createBooking(@RequestBody BookingDto booking) {
    return bookingController.save(booking);
  }

  @PutMapping("/{id}/materials")
  public Booking updateBookingMaterials(@PathVariable String id, @RequestBody List<Material> materials) {
    return bookingController.updateMaterials(id, materials);
  }

  @DeleteMapping("/{id}")
  public void deleteBooking(@PathVariable String id) {
    bookingController.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllBookings() {
    bookingController.deleteAll();
  }
}