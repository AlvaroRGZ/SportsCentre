package es.upm.miw.sportscentre.controllers;
import es.upm.miw.sportscentre.models.Notice;
import es.upm.miw.sportscentre.models.daos.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeController {

  @Autowired
  private NoticeRepository noticeRepository ;

  public List<Notice> findAll() {
    return noticeRepository.findAll();
  }

  public Notice findById(String id) {
    return noticeRepository.findById(id).orElse(null);
  }

  public Notice save(Notice sportCentre) {
    return noticeRepository.save(sportCentre);
  }

  public void deleteById(String id) {
    noticeRepository.deleteById(id);
  }

}