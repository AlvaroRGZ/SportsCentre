package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.daos.ComplaintRepository;
import es.upm.miw.sportscentre.models.daos.BookingRepository;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import es.upm.miw.sportscentre.models.daos.UserRepository;
import es.upm.miw.sportscentre.views.dtos.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingController {

  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private MaterialController materialController;
  @Autowired
  private UserController userController;
  @Autowired
  private InstallationController installationController;

  public List<Booking> findAll() {
    return bookingRepository.findAll();
  }

  public Booking findById(String id) {
    return bookingRepository.findById(id).orElse(null);
  }

  public Booking save(BookingDto booking) {
    // Update materials quantity
    return bookingRepository.save(
        Booking.builder()
            .booker(this.userController.findByEmail(booking.getBooker()))
            .installation(this.installationController.findById(booking.getInstallation()))
            .datetime(booking.getDatetime())
            .registrationTime(booking.getRegistrationTime())
            .materials(this.materialController.findByListOfIds(booking.getMaterials()))
            .build()
    );
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