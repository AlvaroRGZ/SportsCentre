package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {
}
