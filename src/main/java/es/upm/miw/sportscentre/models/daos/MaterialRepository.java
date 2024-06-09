package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Material;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepository extends MongoRepository<Material, String> {
}
