package in.aceinvesting.parent.service.impl;

import in.aceinvesting.parent.dto.MarketData;
import in.aceinvesting.parent.entity.MarketDataEntity;
import in.aceinvesting.parent.logger.InfinitiLogger;
import in.aceinvesting.parent.repository.MarketDataRepo;
import in.aceinvesting.parent.service.MarketDataProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
@Service
public class MarketDataProcessorServiceImpl implements MarketDataProcessorService {

    private final MarketDataRepo marketDataRepo;

    private final SimpMessagingTemplate messagingTemplate;

    public MarketDataProcessorServiceImpl(MarketDataRepo marketDataRepo, SimpMessagingTemplate messagingTemplate) {
        this.marketDataRepo = marketDataRepo;
        this.messagingTemplate = messagingTemplate;
    }

    public void processMarketData(MarketData marketData) {
        // Send market data to connected clients
        messagingTemplate.convertAndSend("/topic/live-market", marketData);
        InfinitiLogger.info("Processing market data for symbol: {}", marketData.getSymbol());

        marketDataRepo.findBySymbol(marketData.getSymbol())
                .ifPresentOrElse(existingData -> {
                    // Update dynamic fields (new high/low)
                    updateMarketData(existingData, marketData);
                }, () -> {
                    // Insert new record if not present
                    saveNewMarketData(marketData);
                });
    }

    private void updateMarketData(MarketDataEntity existingData, MarketData newData) {
        BigDecimal currentHigh = existingData.getDayHigh();
        BigDecimal currentLow = existingData.getDayLow();

        existingData.setClosePrice(newData.getClosePrice());
        existingData.setDayHigh(newData.getDayHigh().max(currentHigh));
        existingData.setDayLow(newData.getDayLow().min(currentLow));
        existingData.setLastUpdated(Timestamp.from(Instant.now()));

        marketDataRepo.save(existingData);
        InfinitiLogger.info("Updated market data for: {}", newData.getSymbol());
    }

    private void saveNewMarketData(MarketData newData) {
        MarketDataEntity newEntity = MarketDataEntity.builder()
                .symbol(newData.getSymbol())
                .openPrice(newData.getOpenPrice())
                .closePrice(newData.getClosePrice())
                .dayHigh(newData.getDayHigh())
                .dayLow(newData.getDayLow())
                .lastUpdated(Timestamp.from(Instant.now()))
                .build();

        marketDataRepo.save(newEntity);
        InfinitiLogger.info("Inserted new market data for: {}", newData.getSymbol());
    }
}
