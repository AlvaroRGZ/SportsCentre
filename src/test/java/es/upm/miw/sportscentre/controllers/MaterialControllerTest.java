package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Material;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MaterialControllerTest {
  @Autowired
  private MaterialController materialController;
  @Test
  void testFindAllMaterials() {

    List<Material> materials = this.materialController.findAll();

    assertNotNull(materials);
    assertTrue(!materials.isEmpty());
    assertEquals("Dumbbells", materials.get(1).getName());
  }

  @Test
  void testFindByName() {
    Material material = materialController.findById("1");
    assertNotNull(material);
    assertEquals("Yoga Mats", material.getName());
  }
  @Test
  void testSaveAndDelete() {
    Material newMaterial = new Material();
    newMaterial.setName("Test Material");
    newMaterial.setDescription("Test Description");
    newMaterial.setQuantity(100);

    // Save
    Material savedMaterial = materialController.save(newMaterial);
    assertNotNull(savedMaterial);
    assertEquals("Test Material", savedMaterial.getName());

    // Delete
    materialController.deleteById(savedMaterial.getId());
    assertNull(materialController.findById(savedMaterial.getId()));
  }
}
