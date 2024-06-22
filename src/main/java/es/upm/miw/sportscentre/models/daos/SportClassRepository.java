package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.SportClass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SportClassRepository extends MongoRepository<SportClass, String> {
  List<SportClass> findByAssistantsContaining(String userId);
}
