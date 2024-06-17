package es.upm.miw.sportscentre.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class InstallationTest {

  @Test
  public void testInstallationBuilderAndGetters() {
    String id = "1";
    String name = "Basketball Court";
    String description = "Indoor basketball court";
    Integer capacity = 10;
    BigDecimal rentalPrice = new BigDecimal("50.00");

    Installation installation = Installation.builder()
        .id(id)
        .name(name)
        .description(description)
        .capacity(capacity)
        .rentalPrice(rentalPrice)
        .build();

    assertThat(installation.getId()).isEqualTo(id);
    assertThat(installation.getName()).isEqualTo(name);
    assertThat(installation.getDescription()).isEqualTo(description);
    assertThat(installation.getCapacity()).isEqualTo(capacity);
    assertThat(installation.getRentalPrice()).isEqualTo(rentalPrice);
  }

  @Test
  public void testInstallationSetters() {
    Installation installation = new Installation();

    String id = "2";
    String name = "Soccer Field";
    String description = "Outdoor soccer field";
    Integer capacity = 20;
    BigDecimal rentalPrice = new BigDecimal("100.00");

    installation.setId(id);
    installation.setName(name);
    installation.setDescription(description);
    installation.setCapacity(capacity);
    installation.setRentalPrice(rentalPrice);

    assertThat(installation.getId()).isEqualTo(id);
    assertThat(installation.getName()).isEqualTo(name);
    assertThat(installation.getDescription()).isEqualTo(description);
    assertThat(installation.getCapacity()).isEqualTo(capacity);
    assertThat(installation.getRentalPrice()).isEqualTo(rentalPrice);
  }

  @Test
  public void testNoArgsConstructor() {
    Installation installation = new Installation();

    assertThat(installation.getId()).isNull();
    assertThat(installation.getName()).isNull();
    assertThat(installation.getDescription()).isNull();
    assertThat(installation.getCapacity()).isNull();
    assertThat(installation.getRentalPrice()).isNull();
  }

  @Test
  public void testAllArgsConstructor() {
    String id = "3";
    String name = "Tennis Court";
    String description = "Clay tennis court";
    Integer capacity = 5;
    BigDecimal rentalPrice = new BigDecimal("70.00");

    Installation installation = new Installation(id, name, description, capacity, rentalPrice);

    assertThat(installation.getId()).isEqualTo(id);
    assertThat(installation.getName()).isEqualTo(name);
    assertThat(installation.getDescription()).isEqualTo(description);
    assertThat(installation.getCapacity()).isEqualTo(capacity);
    assertThat(installation.getRentalPrice()).isEqualTo(rentalPrice);
  }
}
