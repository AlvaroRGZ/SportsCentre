package es.upm.miw.sportscentre.models.daos;

import es.upm.miw.sportscentre.models.Complaint;
import es.upm.miw.sportscentre.models.Installation;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.User;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Profile("dev")
@Service
public class DatabaseSeederDev {
  private final MaterialRepository materialRepository;
  private final InstallationRepository installationRepository;
  private final ComplaintRepository complaintRepository;
  private final UserRepository userRepository;

  @Autowired
  public DatabaseSeederDev(MaterialRepository materialRepository,
                           InstallationRepository installationRepository,
                           ComplaintRepository complaintRepository,
                           UserRepository userRepository) {
    this.materialRepository = materialRepository;
    this.installationRepository = installationRepository;
    this.complaintRepository = complaintRepository;
    this.userRepository = userRepository;
    this.deleteAllAndInitializeAndSeedDataBase();
  }

  public void deleteAllAndInitializeAndSeedDataBase() {
    this.deleteAllAndInitialize();
    this.seedDataBaseJava();
  }

  private void deleteAllAndInitialize() {
    this.materialRepository.deleteAll();
    this.installationRepository.deleteAll();
    this.complaintRepository.deleteAll();
    this.userRepository.deleteAll();
    LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
  }

  private void seedDataBaseJava() {
    LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");

    Material[] materials = {
        Material.builder().name("Yoga Mats").description("High-grip mats for yoga classes").quantity(30).id("1").build(),
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

    Installation[] installations = {
        Installation.builder().name("Indoor Basketball Court").description("Full-size indoor basketball court").capacity(30).rentalPrice(BigDecimal.valueOf(50.00)).id("1").build(),
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

    Complaint[] complaints = {
        Complaint.builder().title("Unscheduled Maintenance").body("The gym was closed unexpectedly.").datetime(LocalDateTime.now().minusDays(1)).id("1").build(),
        Complaint.builder().title("Equipment Issue").body("The treadmill is not working properly.").datetime(LocalDateTime.now().minusHours(2)).build(),
        Complaint.builder().title("Customer Service").body("Reception was not helpful.").datetime(LocalDateTime.now().minusWeeks(1)).build(),
        Complaint.builder().title("Overcrowding").body("Too many people during peak hours.").datetime(LocalDateTime.now().minusDays(3)).build(),
        Complaint.builder().title("Hygiene Concern").body("The locker rooms were not clean.").datetime(LocalDateTime.now().minusDays(2)).build(),
        Complaint.builder().title("Membership Fee").body("Discrepancy in the billing amount.").datetime(LocalDateTime.now().minusMonths(1)).build(),
        Complaint.builder().title("Instructor Late").body("The yoga instructor arrived late.").datetime(LocalDateTime.now().minusDays(5)).build(),
        Complaint.builder().title("Parking Issue").body("Parking lot was full.").datetime(LocalDateTime.now().minusDays(7)).build(),
        Complaint.builder().title("No Water").body("Water dispenser was empty for the whole day.").datetime(LocalDateTime.now().minusHours(5)).build(),
        Complaint.builder().title("Air Conditioning").body("Air conditioning was not working in the spin room.").datetime(LocalDateTime.now().minusDays(6)).build(),
        Complaint.builder().title("Client complaint").body("client complaint description.").datetime(LocalDateTime.now().minusDays(7)).build(),
        Complaint.builder().title("Client a complaint").body("client a complaint description.").datetime(LocalDateTime.now().minusDays(8)).build(),
        Complaint.builder().title("Client a complaint 2").body("client a complaint description 2.").datetime(LocalDateTime.now().minusDays(9)).build()
    };
    this.complaintRepository.saveAll(List.of(complaints));
    LogManager.getLogger(this.getClass()).warn("        ------- Complaints");

    User[] users = {
        User.builder()
            .name("admin")
            .email("admin@gmail.com")
            .password("aaaaaa")
            .role("ADMIN")
            .complaints(List.of())
            .id("1")
            .build(),

        User.builder()
            .name("client")
            .email("client@gmail.com")
            .password("cccccc")
            .role("CLIENT")
            .complaints(List.of(complaints[12]))
            .build(),

        User.builder()
            .name("a")
            .email("a@gmail.com")
            .password("aaaaaa")
            .role("CLIENT")
            .complaints(List.of(complaints[11], complaints[12]))
            .build(),

        User.builder()
            .name("b")
            .email("b@gmail.com")
            .password("bbbbbb")
            .role("CLIENT")
            .complaints(List.of())
            .build(),
    };
    this.userRepository.saveAll(List.of(users));
    LogManager.getLogger(this.getClass()).warn("        ------- Users");
  }
}
