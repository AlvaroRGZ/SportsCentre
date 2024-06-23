package es.upm.miw.sportscentre.controllers;


import es.upm.miw.sportscentre.models.Installation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InstallationControllerTest {

  @Autowired
  private InstallationController installationController;

  @Test
  void testFindAllInstallations() {
    List<Installation> installations = installationController.findAll();
    assertNotNull(installations);
    assertFalse(installations.isEmpty());
    assertEquals("Indoor Basketball Court", installations.get(0).getName());
  }

  @Test
  void testFindById() {
    Installation installation = installationController.findById("1");
    assertNotNull(installation);
    assertEquals("Indoor Basketball Court", installation.getName());
  }

  @Test
  void testSaveAndDelete() {
    Installation newInstallation = new Installation();
    newInstallation.setName("Test Installation");
    newInstallation.setDescription("Test Description");
    newInstallation.setCapacity(50);
    newInstallation.setRentalPrice(BigDecimal.valueOf(100.00));

    // Save
    Installation savedInstallation = installationController.save(newInstallation);
    assertNotNull(savedInstallation);
    assertEquals("Test Installation", savedInstallation.getName());

    // Delete
    installationController.deleteById(savedInstallation.getId());
    assertNull(installationController.findById(savedInstallation.getId()));
  }

  @Test
  void testUpdateInstallation() {
    Installation originalInstallation = installationController.findById("1");
    assertNotNull(originalInstallation);

    // Save the original state
    Installation originalState = new Installation();
    originalState.setId(originalInstallation.getId());
    originalState.setName(originalInstallation.getName());
    originalState.setDescription(originalInstallation.getDescription());
    originalState.setCapacity(originalInstallation.getCapacity());
    originalState.setRentalPrice(originalInstallation.getRentalPrice());

    // Update
    originalInstallation.setName("Updated Installation");
    originalInstallation.setDescription("Updated Description");
    originalInstallation.setCapacity(75);
    originalInstallation.setRentalPrice(BigDecimal.valueOf(150.00));

    Installation updatedInstallation = installationController.update("1", originalInstallation);
    assertNotNull(updatedInstallation);
    assertEquals("Updated Installation", updatedInstallation.getName());
    assertEquals("Updated Description", updatedInstallation.getDescription());
    assertEquals(75, updatedInstallation.getCapacity());
    assertEquals(BigDecimal.valueOf(150.00), updatedInstallation.getRentalPrice());

    // Restore the original state
    installationController.update("1", originalState);
  }

  @Test
  void testIsNameAvailable() {
    boolean isAvailable = installationController.isNameAvailable("New Installation");
    assertTrue(isAvailable);

    // Check with existing name
    isAvailable = installationController.isNameAvailable("Indoor Basketball Court");
    assertFalse(isAvailable);
  }
}
