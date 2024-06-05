package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepository extends MongoRepository<Notice, String> {
}