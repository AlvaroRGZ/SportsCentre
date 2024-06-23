package es.upm.miw.sportscentre.controllers;
import es.upm.miw.sportscentre.models.Notice;
import es.upm.miw.sportscentre.models.daos.NoticeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class NoticeController {

  @Autowired
  private NoticeRepository noticeRepository ;

  public List<Notice> findAll() {
    return noticeRepository.findAll();
  }

  public Notice findById(String id) {
    return noticeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Notice not found"));
  }

  public Notice save(Notice notice) {
    return noticeRepository.save(Notice.builder()
        .title(notice.getTitle())
        .body(notice.getBody())
        .dateTime(notice.getDateTime())
        .build());
  }

  public Notice update(String id, Notice notice) {
    Notice existingNotice = this.findById(id);
    BeanUtils.copyProperties(notice, existingNotice);
    return this.noticeRepository.save(existingNotice);
  }

  public void deleteById(String id) {
    noticeRepository.deleteById(id);
  }

  public void deleteAllNoticesBeforeDate() {
    noticeRepository.deleteAll();
  }

}