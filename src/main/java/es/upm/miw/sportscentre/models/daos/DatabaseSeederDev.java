package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Installation;
import es.upm.miw.sportscentre.models.Material;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Profile("!test")
@Service
public class DatabaseSeederDev {
  private final MaterialRepository materialRepository;
  private final InstallationRepository installationRepository;

  @Autowired
  public DatabaseSeederDev(MaterialRepository materialRepository,
                           InstallationRepository installationRepository) {
    this.materialRepository = materialRepository;
    this.installationRepository = installationRepository;
    this.deleteAllAndInitializeAndSeedDataBase();
  }

  public void deleteAllAndInitializeAndSeedDataBase(){
    this.deleteAllAndInitialize();
    this.seedDataBaseJava();
  }

  private void deleteAllAndInitialize() {
    this.materialRepository.deleteAll();
    this.installationRepository.deleteAll();
    LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
  }

  private void seedDataBaseJava() {
    LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
    Material[] materials = {
        Material.builder().name("Yoga Mats").description("High-grip mats for yoga classes").quantity(30).build(),
        Material.builder().name("Dumbbells").description("Set of 5-50 lb dumbbells for weight training").quantity(40).build(),
        Material.builder().name("Resistance Bands").description("Variety of resistance bands for strength training").quantity(50).build(),
        Material.builder().name("Kettlebells").description("Range of kettlebells from 10 to 50 lbs").quantity(20).build(),
        Material.builder().name("Medicine Balls").description("Set of medicine balls ranging from 4 to 20 lbs").quantity(25).build(),
        Material.builder().name("Treadmills").description("High-quality treadmills with digital displays").quantity(10).build(),
        Material.builder().name("Exercise Bikes").description("Stationary bikes for cardiovascular workouts").quantity(15).build(),
        Material.builder().name("Jump Ropes").description("Adjustable jump ropes for cardio exercises").quantity(60).build(),
        Material.builder().name("Punching Bags").description("Heavy bags for boxing and kickboxing training").quantity(8).build(),
        Material.builder().name("Foam Rollers").description("Foam rollers for post-workout muscle recovery").quantity(35).build()
    };
    this.materialRepository.saveAll(List.of(materials));
    LogManager.getLogger(this.getClass()).warn("        ------- materials");

    LogManager.getLogger(this.getClass()).warn("------- Initial Load of Installations from JAVA -----------");
    Installation[] installations = {
        Installation.builder().name("Indoor Basketball Court").description("Full-size indoor basketball court").capacity(30).rentalPrice(BigDecimal.valueOf(50.00)).build(),
        Installation.builder().name("Swimming Pool").description("Olympic size swimming pool").capacity(100).rentalPrice(BigDecimal.valueOf(100.00)).build(),
        Installation.builder().name("Tennis Court").description("Outdoor clay tennis court").capacity(4).rentalPrice(BigDecimal.valueOf(30.00)).build(),
        Installation.builder().name("Fitness Studio").description("Studio for fitness classes and activities").capacity(25).rentalPrice(BigDecimal.valueOf(40.00)).build(),
        Installation.builder().name("Soccer Field").description("Full-size outdoor soccer field").capacity(22).rentalPrice(BigDecimal.valueOf(60.00)).build(),
        Installation.builder().name("Yoga Room").description("Quiet room for yoga and meditation").capacity(15).rentalPrice(BigDecimal.valueOf(25.00)).build(),
        Installation.builder().name("Weight Room").description("Room with weight training equipment").capacity(20).rentalPrice(BigDecimal.valueOf(35.00)).build(),
        Installation.builder().name("Spinning Room").description("Room with stationary bikes for spinning classes").capacity(20).rentalPrice(BigDecimal.valueOf(35.00)).build(),
        Installation.builder().name("Boxing Ring").description("Professional boxing ring for training and matches").capacity(10).rentalPrice(BigDecimal.valueOf(45.00)).build(),
        Installation.builder().name("Sauna").description("Sauna room for relaxation and recovery").capacity(8).rentalPrice(BigDecimal.valueOf(20.00)).build()
    };
    this.installationRepository.saveAll(List.of(installations));
    LogManager.getLogger(this.getClass()).warn("        ------- Installations");
  }

}
