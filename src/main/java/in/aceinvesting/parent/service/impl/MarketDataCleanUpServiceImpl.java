package in.aceinvesting.parent.service.impl;

import in.aceinvesting.parent.repository.MarketDataRepo;
import in.aceinvesting.parent.service.MarketDataCleanUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
@Service
public class MarketDataCleanUpServiceImpl implements MarketDataCleanUpService {

    private final MarketDataRepo marketDataRepo;

    public MarketDataCleanUpServiceImpl(MarketDataRepo marketDataRepo) {
        this.marketDataRepo = marketDataRepo;
    }

    @Override
    public int triggerCleanUpMarketData(LocalDateTime todayStart, LocalDateTime todayEnd, LocalDateTime opening, LocalDateTime closing) {
        return marketDataRepo.deleteBetweenTimes(todayStart, todayEnd, opening, closing);
    }
}
