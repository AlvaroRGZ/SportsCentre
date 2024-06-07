package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
