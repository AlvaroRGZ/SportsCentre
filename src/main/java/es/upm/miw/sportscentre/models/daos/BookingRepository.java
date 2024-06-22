package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
  List<Booking> findByInstallationId(String installationId);
  List<Booking> findByBookerId(String bookerId);
}
