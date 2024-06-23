package es.upm.miw.sportscentre.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

  @Test
  void testBuilderAndGetters() {
    Material material = Material.builder()
        .id("1")
        .name("Yoga Mat")
        .description("High-grip mat for yoga classes")
        .quantity(30)
        .build();

    assertEquals("1", material.getId());
    assertEquals("Yoga Mat", material.getName());
    assertEquals("High-grip mat for yoga classes", material.getDescription());
    assertEquals(30, material.getQuantity());
  }

  @Test
  void testSetters() {
    Material material = new Material();
    material.setId("2");
    material.setName("Dumbbell");
    material.setDescription("Set of 5-50 lb dumbbells for weight training");
    material.setQuantity(40);

    assertEquals("2", material.getId());
    assertEquals("Dumbbell", material.getName());
    assertEquals("Set of 5-50 lb dumbbells for weight training", material.getDescription());
    assertEquals(40, material.getQuantity());
  }

  @Test
  void testNoArgsConstructor() {
    Material material = new Material();
    assertNull(material.getId());
    assertNull(material.getName());
    assertNull(material.getDescription());
    assertNull(material.getQuantity());
  }

  @Test
  void testAllArgsConstructor() {
    Material material = new Material("3", "Resistance Band", "Variety of resistance bands for strength training", 50);

    assertEquals("3", material.getId());
    assertEquals("Resistance Band", material.getName());
    assertEquals("Variety of resistance bands for strength training", material.getDescription());
    assertEquals(50, material.getQuantity());
  }

  @Test
  void testAddQuantity() {
    Material material = Material.builder().quantity(10).build();
    material.addQuantity(5);
    assertEquals(15, material.getQuantity());
  }

  @Test
  void testRemoveQuantity() {
    Material material = Material.builder().quantity(10).build();
    material.removeQuantity(5);
    assertEquals(5, material.getQuantity());
  }

  @Test
  void testRemoveQuantityToZero() {
    Material material = Material.builder().quantity(10).build();
    material.removeQuantity(15);
    assertEquals(0, material.getQuantity());
  }

  @Test
  void testEqualityAndHashCode() {
    Material material1 = Material.builder()
        .id("1")
        .name("Yoga Mat")
        .description("High-grip mat for yoga classes")
        .quantity(30)
        .build();

    Material material2 = Material.builder()
        .id("1")
        .name("Yoga Mat")
        .description("High-grip mat for yoga classes")
        .quantity(30)
        .build();

    assertEquals(material1, material2);
    assertEquals(material1.hashCode(), material2.hashCode());
  }

  @Test
  void testToString() {
    Material material = Material.builder()
        .id("1")
        .name("Yoga Mat")
        .description("High-grip mat for yoga classes")
        .quantity(30)
        .build();

    String expectedString = "Material(id=1, name=Yoga Mat, description=High-grip mat for yoga classes, quantity=30)";
    assertEquals(expectedString, material.toString());
  }
}
