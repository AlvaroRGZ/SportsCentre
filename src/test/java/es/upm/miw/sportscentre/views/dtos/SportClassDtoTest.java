package es.upm.miw.sportscentre.views.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SportClassDtoTest {

  @Test
  void testBuilderAndGetters() {
    SportClassDto sportClassDto = SportClassDto.builder()
        .title("Yoga Class")
        .installation("1")
        .places(20)
        .build();

    assertEquals("Yoga Class", sportClassDto.getTitle());
    assertEquals("1", sportClassDto.getInstallation());
    assertEquals(20, sportClassDto.getPlaces());
  }

  @Test
  void testSetters() {
    SportClassDto sportClassDto = new SportClassDto();
    sportClassDto.setTitle("Pilates Class");
    sportClassDto.setInstallation("2");
    sportClassDto.setPlaces(25);

    assertEquals("Pilates Class", sportClassDto.getTitle());
    assertEquals("2", sportClassDto.getInstallation());
    assertEquals(25, sportClassDto.getPlaces());
  }

  @Test
  void testNoArgsConstructor() {
    SportClassDto sportClassDto = new SportClassDto();
    assertNull(sportClassDto.getTitle());
    assertNull(sportClassDto.getInstallation());
    assertNull(sportClassDto.getPlaces());
  }

  @Test
  void testAllArgsConstructor() {
    SportClassDto sportClassDto = new SportClassDto("Zumba Class", "3", 30);

    assertEquals("Zumba Class", sportClassDto.getTitle());
    assertEquals("3", sportClassDto.getInstallation());
    assertEquals(30, sportClassDto.getPlaces());
  }

  @Test
  void testEqualityAndHashCode() {
    SportClassDto sportClassDto1 = SportClassDto.builder()
        .title("Yoga Class")
        .installation("1")
        .places(20)
        .build();

    SportClassDto sportClassDto2 = SportClassDto.builder()
        .title("Yoga Class")
        .installation("1")
        .places(20)
        .build();

    assertEquals(sportClassDto1, sportClassDto2);
    assertEquals(sportClassDto1.hashCode(), sportClassDto2.hashCode());
  }

  @Test
  void testToString() {
    SportClassDto sportClassDto = SportClassDto.builder()
        .title("Yoga Class")
        .installation("1")
        .places(20)
        .build();

    String expectedString = "SportClassDto(title=Yoga Class, installation=1, places=20)";
    assertEquals(expectedString, sportClassDto.toString());
  }
}
