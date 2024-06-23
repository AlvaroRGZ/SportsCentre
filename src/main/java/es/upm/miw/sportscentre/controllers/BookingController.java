package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Booking;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.User;
import es.upm.miw.sportscentre.models.daos.ComplaintRepository;
import es.upm.miw.sportscentre.models.daos.BookingRepository;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import es.upm.miw.sportscentre.models.daos.UserRepository;
import es.upm.miw.sportscentre.views.dtos.BookingDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

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
    return bookingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Booking not found"));
  }

  public List<Booking> findByInstallationId(String installationId) {
    return bookingRepository.findByInstallationId(installationId);
  }

  public Booking save(BookingDto booking) {
    booking.getMaterials().forEach((materialId) -> {
      Material material = this.materialController.findById(materialId);
      material.removeQuantity(1);
      this.materialController.save(material);
    });

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
      material.addQuantity(1);
      this.materialController.save(material);
    });
    bookingRepository.deleteById(id);
  }

  public void deleteAll() {
    this.bookingRepository.deleteAll();
  }

  public List<Booking> findByBookerEmail(String email) {
    User user = this.userController.findByEmail(email);
    return this.bookingRepository.findByBookerId(user.getId());
  }

  public Booking updateMaterials(String id, List<Material> materials) {
    Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    booking.setMaterials(materials);
    LogManager.getLogger(this.getClass()).warn("updated ");
    return bookingRepository.save(booking);
  }
}