package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Installation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstallationRepository  extends MongoRepository<Installation, String> {
  boolean existsByName(String name);
}
