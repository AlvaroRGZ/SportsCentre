package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Installation;
import es.upm.miw.sportscentre.models.SportClass;
import es.upm.miw.sportscentre.models.User;
import es.upm.miw.sportscentre.models.daos.SportClassRepository;
import es.upm.miw.sportscentre.views.dtos.SportClassCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportClassController {

  @Autowired
  private SportClassRepository sportClassRepository;
  @Autowired
  private UserController userController;
  @Autowired
  private InstallationController installationController;

  public List<SportClass> findAll() {
    return sportClassRepository.findAll();
  }

  public SportClass findById(String id) {
    return sportClassRepository.findById(id).orElseThrow(() -> new RuntimeException("Sportclass not found"));
  }

  public List<SportClass> findByUserEmail(String email) {
    User user = this.userController.findByEmail(email);
    return this.sportClassRepository.findByAssistantsContaining(user.getId());
  }

  public SportClass save(SportClassCreationDto sportClassDto) {
    Installation installation = this.installationController.findById(sportClassDto.getInstallation());

    return sportClassRepository.save(SportClass.builder()
        .title(sportClassDto.getTitle())
        .installation(installation)
        .places(sportClassDto.getPlaces())
        .assistants(List.of())
        .build());
  }

  public SportClass addAssistantByEmail(String sportClassId, String email) {
    SportClass sportClass = this.findById(sportClassId);
      User user = this.userController.findByEmail(email);
      List<User> assistants = sportClass.getAssistants();
      if (!assistants.contains(user)) {
        assistants.add(user);
        sportClass.setAssistants(assistants);
        return sportClassRepository.save(sportClass);
      }
    return sportClass;
  }

  public SportClass removeAssistantByEmail(String sportClassId, String email) {
    SportClass sportClass = this.findById(sportClassId);
    User user = this.userController.findByEmail(email);
    List<User> assistants = sportClass.getAssistants();
    assistants.remove(user);
    sportClass.setAssistants(assistants);
    return sportClassRepository.save(sportClass);
  }

  public void deleteById(String id) {
    sportClassRepository.deleteById(id);
  }
}
