package aparatado1_1_SinRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Reto1U5Application implements CommandLineRunner {

  @Autowired
  private ClienteRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Reto1U5Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    repository.deleteAll();

    // save a couple of customers
    repository.save(new Cliente("Alice", "Smith"));
    repository.save(new Cliente("Bob", "Smith"));

    // fetch all customers
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    for (Cliente customer : repository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("Customer found with findByFirstName('Alice'):");
    System.out.println("--------------------------------");
    System.out.println(repository.findByNombre("Alice"));

    System.out.println("Customers found with findByLastName('Smith'):");
    System.out.println("--------------------------------");
    for (Cliente customer : repository.findByApellido("Smith")) {
      System.out.println(customer);
    }

  }

}
