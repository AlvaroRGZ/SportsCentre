package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Installation;
import es.upm.miw.sportscentre.models.daos.InstallationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

  public Installation update(String installationId, Installation installation) {
    Installation existingInstallation = installationRepository.findById(installationId).orElseThrow(() -> new RuntimeException("Booking not found"));
    existingInstallation.setName(installation.getName());
    existingInstallation.setDescription(installation.getDescription());
    existingInstallation.setCapacity(installation.getCapacity());
    existingInstallation.setRentalPrice(installation.getRentalPrice());
    return installationRepository.save(existingInstallation);
  }

  public boolean isNameAvailable(String name) {
    return !installationRepository.existsByName(name);
  }
}