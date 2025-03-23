package in.aceinvesting.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: Kush Gudhka
 */
@SpringBootApplication(scanBasePackages = "in.aceinvesting.parent")
@EnableScheduling
public class InfinitiParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfinitiParentApplication.class, args);
    }
}