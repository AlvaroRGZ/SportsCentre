package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Installation;
import es.upm.miw.sportscentre.models.daos.InstallationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstallationController {

  @Autowired
  private InstallationRepository installationRepository ;

  public List<Installation> findAll() {
    return installationRepository.findAll();
  }

  public Installation findById(String id) {
    return installationRepository.findById(id).orElse(null);
  }

  public Installation save(Installation Installation) {
    return installationRepository.save(Installation);
  }

  public void deleteById(String id) {
    installationRepository.deleteById(id);
  }

  public void deleteAllInstallations() {
    installationRepository.deleteAll();
  }

}