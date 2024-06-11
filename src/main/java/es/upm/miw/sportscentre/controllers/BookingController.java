package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.daos.ComplaintRepository;
import es.upm.miw.sportscentre.models.daos.BookingRepository;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import es.upm.miw.sportscentre.models.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingController {

  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private MaterialController materialController;

  public List<Booking> findAll() {
    return bookingRepository.findAll();
  }

  public Booking findById(String id) {
    return bookingRepository.findById(id).orElse(null);
  }

  public Booking save(Booking booking) {
    // Update materials quantity
    return bookingRepository.save(booking);
  }

  public void deleteById(String id) {
    this.findById(id).getMaterials().forEach(material -> {
      this.materialController.findById(material.getId())
          .addQuantity(1);
      //this.materialController.save()
    });
    bookingRepository.deleteById(id);
  }

  public void deleteAll() {
    this.bookingRepository.deleteAll();
  }
}