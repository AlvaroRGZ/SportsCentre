package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.NoticeController;
import es.upm.miw.sportscentre.models.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notices")
public class NoticeView {

  @Autowired
  private NoticeController noticeController;

  @GetMapping
  public List<Notice> getAll() {
    return noticeController.findAll();
  }

  @GetMapping("/{id}")
  public Notice getNoticeById(@PathVariable String id) {
    return noticeController.findById(id);
  }

  @PostMapping
  public Notice createNotice(@RequestBody Notice notice) {
    // Notice n = Notice.builder().title("hola").body("Esto es una gran noticia creada a las: " + LocalDateTime.now()).build();¡
    notice.setBody(notice.getBody() + "\nCreada a las: " + LocalDateTime.now());
    return noticeController.save(notice);
  }

  @DeleteMapping("/{id}")
  public void deleteNotice(@PathVariable String id) {
    noticeController.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllNoticesBeforeDate() {
    noticeController.deleteAllNoticesBeforeDate();
  }
}