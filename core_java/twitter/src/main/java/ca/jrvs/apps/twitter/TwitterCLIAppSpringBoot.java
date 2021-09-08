package ca.jrvs.apps.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")
public class TwitterCLIAppSpringBoot implements CommandLineRunner {

  private TwitterCLIApp app;

  @Autowired
  public TwitterCLIAppSpringBoot(TwitterCLIApp app){
    this.app = app;
  }

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(TwitterCLIAppSpringBoot.class);

    //web off
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    app.run(args);
  }
}
