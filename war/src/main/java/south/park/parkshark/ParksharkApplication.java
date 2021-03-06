package south.park.parkshark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(scanBasePackages = "south.park.parkshark")
public class ParksharkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParksharkApplication.class, args);
    }

}
