package in.aceinvesting.parent.controller;

import com.zaxxer.hikari.HikariDataSource;
import in.aceinvesting.parent.logger.InfinitiLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kush Gudhka
 */

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    private final HikariDataSource hikariDataSource;

    public TestController(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @GetMapping("/getServiceStatus")
    public String getStatus() {
        InfinitiLogger.info("Up And Running");
        return "Infiniti: Up And Running";
    }

    @GetMapping("/getDbStatus")
    public String getDbStatus() {
        if (!hikariDataSource.isClosed()) {
            InfinitiLogger.info("Database Is Active.");
            return "Infiniti: Database Is Active";
        }
        InfinitiLogger.info("DATABASE IS INACTIVE");
        return "Infiniti: DATABASE IS INACTIVE";
    }
}