package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.daos.NoticeRepository;
import es.upm.miw.sportscentre.models.User;
import es.upm.miw.sportscentre.models.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private NoticeRepository noticeRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {
    return userRepository.findById(id).orElse(null);
  }

  public User save(User user) {
    user.getNotices().forEach((notice -> {
      this.noticeRepository.save(notice);
    }));
    return userRepository.save(user);
  }

  public void deleteById(String id) {
    userRepository.deleteById(id);
  }
}