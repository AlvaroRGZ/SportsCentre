package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
  User findUserByEmail(String email);
  boolean existsByEmail(String email);
  @Query("{ 'complaints': { $exists: true, $ne: [] } }")
  List<User> findUsersWithNonEmptyComplaints();
 }
