package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
