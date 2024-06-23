package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MaterialControllerTest {
  @Autowired
  private MaterialController materialController;
  @Test
  void testFindAllMaterials() {

    List<Material> materials = this.materialController.findAll();

    assertNotNull(materials);
    assertTrue(materials.size() > 0);
    assertEquals("Yoga Mats", materials.get(0).getName());
  }
}
