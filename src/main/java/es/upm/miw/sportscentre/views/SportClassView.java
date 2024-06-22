package es.upm.miw.sportscentre.views;


import es.upm.miw.sportscentre.controllers.SportClassController;
import es.upm.miw.sportscentre.models.SportClass;
import es.upm.miw.sportscentre.views.dtos.SportClassCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportclasses")
public class SportClassView {

  @Autowired
  private SportClassController sportClassController;

  @GetMapping
  public List<SportClass> getAllSportClasses() {
    return this.sportClassController.findAll();
  }

  @GetMapping("/{id}")
  public SportClass getSportClassById(@PathVariable String id) {
    return this.sportClassController.findById(id);
  }

  @GetMapping("/user/{email}")
  public List<SportClass> getSportClassesByUserEmail(@PathVariable String email) {
    return this.sportClassController.findByUserEmail(email);
  }

  @PostMapping
  public SportClass createSportClass(@RequestBody SportClassCreationDto sportClassDto) {

    return this.sportClassController.save(sportClassDto);
  }

  @PutMapping("/{id}/addAssistant")
  public SportClass addAssistantToSportClass(@PathVariable String id, @RequestParam String email) {
    return this.sportClassController.addAssistantByEmail(id, email);
  }

  @PutMapping("/{id}/removeAssistant")
  public SportClass removeAssistantFromSportClass(@PathVariable String id, @RequestParam String email) {
    return this.sportClassController.removeAssistantByEmail(id, email);
  }

  @DeleteMapping("/{id}")
  public void deleteSportClass(@PathVariable String id) {
    this.sportClassController.deleteById(id);
  }
}