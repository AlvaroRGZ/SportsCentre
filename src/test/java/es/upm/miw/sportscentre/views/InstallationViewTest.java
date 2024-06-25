package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.InstallationController;
import es.upm.miw.sportscentre.models.Installation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "server.port=4201")
public class InstallationViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private InstallationController installationController;

  @Test
  void testGetAllInstallations() {
    ResponseEntity<Installation[]> response = restTemplate.getForEntity("/installations", Installation[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Installation[] installations = response.getBody();
    assertNotNull(installations);
    assertTrue(installations.length > 0);
  }

  @Test
  void testGetInstallationById() {
    ResponseEntity<Installation> response = restTemplate.getForEntity("/installations/1", Installation.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Installation installation = response.getBody();
    assertNotNull(installation);
    assertEquals("Indoor Basketball Court", installation.getName());
  }

  @Test
  void testCheckNameAvailability() {
    ResponseEntity<Boolean> response = restTemplate.getForEntity("/installations/check-name?name=New Installation", Boolean.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody());

    response = restTemplate.getForEntity("/installations/check-name?name=Indoor Basketball Court", Boolean.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertFalse(response.getBody());
  }

  @Test
  void testCreateInstallation() {
    Installation newInstallation = Installation.builder()
        .name("Test Installation")
        .description("Test Description")
        .capacity(50)
        .rentalPrice(BigDecimal.valueOf(100.00))
        .build();
    ResponseEntity<Installation> response = restTemplate.postForEntity("/installations", newInstallation, Installation.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Installation savedInstallation = response.getBody();
    assertNotNull(savedInstallation);
    assertEquals("Test Installation", savedInstallation.getName());
  }

  @Test
  void testUpdateInstallation() {
    Installation originalInstallation = installationController.findById("1");
    Installation backupInstallation = Installation.builder()
        .id(originalInstallation.getId())
        .name(originalInstallation.getName())
        .description(originalInstallation.getDescription())
        .capacity(originalInstallation.getCapacity())
        .rentalPrice(originalInstallation.getRentalPrice())
        .build();

    originalInstallation.setName("Updated Installation");
    originalInstallation.setDescription("Updated Description");

    restTemplate.put("/installations/1", originalInstallation);

    Installation updatedInstallation = installationController.findById("1");
    assertNotNull(updatedInstallation);
    assertEquals("Updated Installation", updatedInstallation.getName());
    assertEquals("Updated Description", updatedInstallation.getDescription());

    // Restore original installation
    restTemplate.put("/installations/1", backupInstallation);
    Installation restoredInstallation = installationController.findById("1");
    assertNotNull(restoredInstallation);
    assertEquals(backupInstallation.getName(), restoredInstallation.getName());
    assertEquals(backupInstallation.getDescription(), restoredInstallation.getDescription());
  }

  @Test
  void testDeleteInstallation() {
    Installation newInstallation = Installation.builder()
        .name("Installation to Delete")
        .description("This installation will be deleted")
        .capacity(30)
        .rentalPrice(BigDecimal.valueOf(50.00))
        .build();
    Installation savedInstallation = installationController.save(newInstallation);

    restTemplate.delete("/installations/" + savedInstallation.getId());

    Exception exception = assertThrows(RuntimeException.class, () -> {
      installationController.findById(savedInstallation.getId());
    });
    assertEquals("Installation not found", exception.getMessage());
  }
}
