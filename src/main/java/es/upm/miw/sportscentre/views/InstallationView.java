package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.InstallationController;
import es.upm.miw.sportscentre.models.Installation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/installations")
public class InstallationView {

  @Autowired
  private InstallationController installationController;

  @GetMapping
  public List<Installation> getAll() {
    return installationController.findAll();
  }

  @GetMapping("/{id}")
  public Installation getInstallationById(@PathVariable String id) {
    return installationController.findById(id);
  }

  @PostMapping
  public Installation createInstallation(@RequestBody Installation installation) {
    return installationController.save(installation);
  }

  @PutMapping("/{id}")
  public Installation updateInstallation(@PathVariable String id, @RequestBody Installation installation) {
    return installationController.update(id, installation);
  }

  @DeleteMapping("/{id}")
  public void deleteInstallation(@PathVariable String id) {
    installationController.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllInstallations() {
    installationController.deleteAllInstallations();
  }
}