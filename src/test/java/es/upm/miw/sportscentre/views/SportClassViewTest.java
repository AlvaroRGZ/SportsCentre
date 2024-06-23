package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.SportClassController;
import es.upm.miw.sportscentre.models.SportClass;
import es.upm.miw.sportscentre.views.dtos.SportClassDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SportClassViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private SportClassController sportClassController;

  @Test
  void testGetAllSportClasses() {
    ResponseEntity<SportClass[]> response = restTemplate.getForEntity("/sportclasses", SportClass[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    SportClass[] sportClasses = response.getBody();
    assertNotNull(sportClasses);
    assertTrue(sportClasses.length > 0);
  }

  @Test
  void testGetSportClassById() {
    ResponseEntity<SportClass> response = restTemplate.getForEntity("/sportclasses/1", SportClass.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    SportClass sportClass = response.getBody();
    assertNotNull(sportClass);
    assertEquals("Yoga Class", sportClass.getTitle());
  }

  @Test
  void testGetSportClassesByUserEmail() {
    ResponseEntity<SportClass[]> response = restTemplate.getForEntity("/sportclasses/user/a@gmail.com", SportClass[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    SportClass[] sportClasses = response.getBody();
    assertNotNull(sportClasses);
    assertTrue(sportClasses.length > 0);
  }

  @Test
  void testCreateSportClass() {
    SportClassDto newSportClass = SportClassDto.builder()
        .title("Pilates Class")
        .installation("1")
        .places(20)
        .build();
    ResponseEntity<SportClass> response = restTemplate.postForEntity("/sportclasses", newSportClass, SportClass.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    SportClass savedSportClass = response.getBody();
    assertNotNull(savedSportClass);
    assertEquals("Pilates Class", savedSportClass.getTitle());
  }

  @Test
  void testAddAssistantToSportClass() {
    restTemplate.put("/sportclasses/1/addAssistant?email=admin@gmail.com", null);
    SportClass updatedSportClass = sportClassController.findById("1");
    assertNotNull(updatedSportClass);
    assertTrue(updatedSportClass.getAssistants().stream().anyMatch(a -> a.getEmail().equals("admin@gmail.com")));
  }

  @Test
  void testRemoveAssistantFromSportClass() {
    restTemplate.put("/sportclasses/1/addAssistant?email=admin@gmail.com", null);
    restTemplate.put("/sportclasses/1/removeAssistant?email=admin@gmail.com", null);
    SportClass updatedSportClass = sportClassController.findById("1");
    assertNotNull(updatedSportClass);
    assertFalse(updatedSportClass.getAssistants().stream().anyMatch(a -> a.getEmail().equals("admin@gmail.com")));
  }

  @Test
  void testDeleteSportClass() {
    SportClassDto newSportClass = SportClassDto.builder()
        .title("Class to Delete")
        .installation("1")
        .places(20)
        .build();
    SportClass savedSportClass = sportClassController.save(newSportClass);
    restTemplate.delete("/sportclasses/" + savedSportClass.getId());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      sportClassController.findById(savedSportClass.getId());
    });
    assertEquals("Sportclass not found", exception.getMessage());
  }
}
