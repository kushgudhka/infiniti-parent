package in.aceinvesting.parent.cron;

import in.aceinvesting.parent.logger.InfinitiLogger;
import in.aceinvesting.parent.service.MarketDataCleanUpService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class MarketDataCleanUp {

    private MarketDataCleanUpService marketDataCleanUpService;

    public MarketDataCleanUp(MarketDataCleanUpService marketDataCleanUpService){
        this.marketDataCleanUpService = marketDataCleanUpService;
    }

    // Cron Job: Runs every day at 11:00 PM IST
    @Scheduled(cron = "0 0 23 * * ?", zone = "Asia/Kolkata")
    public void cleanUpMarketData() {
        InfinitiLogger.info("Starting market data cleanup job...");

        // Get today's 9:15 AM and 3:30 PM IST timestamps
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        LocalDateTime todayStart = now.toLocalDate().atTime(LocalTime.of(9, 16));
        LocalDateTime todayEnd = now.toLocalDate().atTime(LocalTime.of(15, 29));
        LocalDateTime opening = now.toLocalDate().atTime(LocalTime.of(9, 15));
        LocalDateTime closing = now.toLocalDate().atTime(LocalTime.of(15, 30));

        // Delete entries between 9:16 AM and 3:29 PM
        int deletedRecords = marketDataCleanUpService.triggerCleanUpMarketData(todayStart, todayEnd, opening, closing);
        InfinitiLogger.info("Cleanup completed. Deleted {} records.", deletedRecords);
    }
}
