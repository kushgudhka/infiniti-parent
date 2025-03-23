package in.aceinvesting.parent.service;

import java.time.LocalDateTime;

/**
 * @Author: Kush Gudhka
 */
public interface MarketDataCleanUpService {
    int triggerCleanUpMarketData(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime opening, LocalDateTime closing);
}
