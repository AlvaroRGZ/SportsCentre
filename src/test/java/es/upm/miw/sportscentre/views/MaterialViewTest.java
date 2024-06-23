package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.MaterialController;
import es.upm.miw.sportscentre.models.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MaterialViewTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MaterialController materialController;

  @Test
  void testGetAllMaterials() {
    ResponseEntity<Material[]> response = restTemplate.getForEntity("/materials", Material[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Material[] materials = response.getBody();
    assertNotNull(materials);
    assertTrue(materials.length > 0);
  }

  @Test
  void testGetMaterialById() {
    ResponseEntity<Material> response = restTemplate.getForEntity("/materials/1", Material.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Material material = response.getBody();
    assertNotNull(material);
    assertEquals("Yoga Mats", material.getName());
  }

  @Test
  void testCreateMaterial() {
    Material newMaterial = Material.builder()
        .name("Test Material")
        .description("Test Description")
        .quantity(100)
        .build();
    ResponseEntity<Material> response = restTemplate.postForEntity("/materials", newMaterial, Material.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    Material savedMaterial = response.getBody();
    assertNotNull(savedMaterial);
    assertEquals("Test Material", savedMaterial.getName());
  }

  @Test
  void testUpdateMaterial() {
    Material originalMaterial = materialController.findById("1");
    Material backupMaterial = Material.builder()
        .id(originalMaterial.getId())
        .name(originalMaterial.getName())
        .description(originalMaterial.getDescription())
        .quantity(originalMaterial.getQuantity())
        .build();

    originalMaterial.setName("Updated Material");
    originalMaterial.setDescription("Updated Description");

    restTemplate.put("/materials/1", originalMaterial);

    Material updatedMaterial = materialController.findById("1");
    assertNotNull(updatedMaterial);
    assertEquals("Updated Material", updatedMaterial.getName());
    assertEquals("Updated Description", updatedMaterial.getDescription());

    // Restore original material
    restTemplate.put("/materials/1", backupMaterial);
    Material restoredMaterial = materialController.findById("1");
    assertNotNull(restoredMaterial);
    assertEquals(backupMaterial.getName(), restoredMaterial.getName());
    assertEquals(backupMaterial.getDescription(), restoredMaterial.getDescription());
  }

  @Test
  void testDeleteMaterial() {
    Material newMaterial = Material.builder()
        .name("Material to Delete")
        .description("This material will be deleted")
        .quantity(50)
        .build();
    Material savedMaterial = materialController.save(newMaterial);

    restTemplate.delete("/materials/" + savedMaterial.getId());

    Exception exception = assertThrows(RuntimeException.class, () -> {
      materialController.findById(savedMaterial.getId());
    });
    assertEquals("Material not found", exception.getMessage());
  }
}
