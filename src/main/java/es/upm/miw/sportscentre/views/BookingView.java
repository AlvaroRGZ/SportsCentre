package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.BookingController;
import es.upm.miw.sportscentre.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingView {

  @Autowired
  private BookingController bookingController;

  @GetMapping
  public List<Booking> getAll() {
    return bookingController.findAll();
  }

  @GetMapping("/{id}")
  public Booking getBookingById(@PathVariable String id) {
    return bookingController.findById(id);
  }

  @PostMapping
  public Booking createBooking(@RequestBody Booking user) {
    return bookingController.save(user);
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