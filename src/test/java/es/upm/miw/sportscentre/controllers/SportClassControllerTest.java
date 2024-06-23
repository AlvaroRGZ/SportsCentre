package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.SportClass;
import es.upm.miw.sportscentre.views.dtos.SportClassCreationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SportClassControllerTest {

  @Autowired
  private SportClassController sportClassController;

  @Autowired
  private UserController userController;

  @Autowired
  private InstallationController installationController;

  @Test
  void testFindAll() {
    List<SportClass> sportClasses = sportClassController.findAll();
    assertNotNull(sportClasses);
    assertFalse(sportClasses.isEmpty());
  }

  @Test
  void testFindById() {
    SportClass sportClass = sportClassController.findById("1");
    assertNotNull(sportClass);
    assertEquals("Yoga Class", sportClass.getTitle());
  }

  @Test
  void testFindByUserEmail() {
    List<SportClass> sportClasses = sportClassController.findByUserEmail("a@gmail.com");
    assertNotNull(sportClasses);
    assertFalse(sportClasses.isEmpty());
  }

  @Test
  void testSaveAndDelete() {
    SportClassCreationDto newSportClassDto = SportClassCreationDto.builder()
        .title("Test Sport Class")
        .places(20)
        .installation("1")
        .build();

    // Save
    SportClass savedSportClass = sportClassController.save(newSportClassDto);
    assertNotNull(savedSportClass);
    assertEquals("Test Sport Class", savedSportClass.getTitle());

    // Delete
    sportClassController.deleteById(savedSportClass.getId());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      sportClassController.findById(savedSportClass.getId());
    });
    assertEquals("Sportclass not found", exception.getMessage());
  }

  @Test
  void testAddAndRemoveAssistantByEmail() {
    String sportClassId = "1"; // Assume this ID exists
    String email = "admin@gmail.com"; // Assume this email exists

    // Add Assistant
    SportClass sportClass = sportClassController.addAssistantByEmail(sportClassId, email);
    assertTrue(sportClass.getAssistants().stream().anyMatch(user -> user.getEmail().equals(email)));

    // Remove Assistant
    sportClass = sportClassController.removeAssistantByEmail(sportClassId, email);
    assertFalse(sportClass.getAssistants().stream().anyMatch(user -> user.getEmail().equals(email)));
  }

  @Test
  void testSaveSportClassWithNonexistentInstallation() {
    SportClassCreationDto newSportClassDto = SportClassCreationDto.builder()
        .title("Test Sport Class")
        .places(20)
        .installation("nonexistent_installation_id")
        .build();

    Exception exception = assertThrows(RuntimeException.class, () -> {
      sportClassController.save(newSportClassDto);
    });
    assertEquals("Installation not found", exception.getMessage());
  }

  @Test
  void testAddAssistantByEmailToNonexistentSportClass() {

    Exception exception = assertThrows(RuntimeException.class, () -> {
      sportClassController.addAssistantByEmail("nonexistent_sport_class_id", "admin@gmail.com");
    });
    assertEquals("Sportclass not found", exception.getMessage());
  }

  @Test
  void testRemoveAssistantByEmailFromNonexistentSportClass() {
    String sportClassId = "nonexistent_sport_class_id";
    String email = "admin@gmail.com";

    Exception exception = assertThrows(RuntimeException.class, () -> {
      sportClassController.removeAssistantByEmail(sportClassId, email);
    });
    assertEquals("Sportclass not found", exception.getMessage());
  }
}
