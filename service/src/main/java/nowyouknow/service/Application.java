package nowyouknow.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("nowyouknow.common.data")
@EnableJpaRepositories("nowyouknow.common.dao")
public class Application {
  // http://stackoverflow.com/questions/28723425/logging-controller-requests-spring-boot
  
  public Application() {}

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
