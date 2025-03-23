package in.aceinvesting.parent.controller;

import in.aceinvesting.parent.dto.MarketData;
import in.aceinvesting.parent.service.MarketDataProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
@Controller
public class WebsocketController {


    private final MarketDataProcessorService marketDataProcessor;

    public WebsocketController(MarketDataProcessorService marketDataProcessor) {
        this.marketDataProcessor = marketDataProcessor;
    }

    @MessageMapping("/process-market")
    @SendTo("/topic/live-market")
    public MarketData handleMarketData(MarketData marketData) {
        log.info("INFINITI : Received WebSocket Market Data: {}", marketData);
        marketDataProcessor.processMarketData(marketData);
        return marketData;
    }
}
