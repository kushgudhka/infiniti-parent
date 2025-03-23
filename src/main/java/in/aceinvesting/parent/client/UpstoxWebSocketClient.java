package in.aceinvesting.parent.client;

import in.aceinvesting.parent.config.UpstoxConfig;
import in.aceinvesting.parent.dto.MarketData;
import in.aceinvesting.parent.service.MarketDataProcessorService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketClient;

import java.util.List;

/**
 * @Author: Kush Gudhka
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UpstoxWebSocketClient implements WebSocketHandler {

    private final UpstoxConfig upstoxConfig;
    private final MarketDataProcessorService marketDataProcessorService;
    private final WebSocketClient webSocketClient = new StandardWebSocketClient();
    private WebSocketSession session;

    @PostConstruct
    public void connect() {
        try {
            String url = upstoxConfig.getWebSocketUrl() + "?apiKey=" + upstoxConfig.getApiKey() +
                    "&access_token=" + upstoxConfig.getAccessToken();
            this.session = webSocketClient.doHandshake(this, url).get();
            log.info("✅ Connected to Upstox WebSocket");
        } catch (Exception e) {
            log.error("❌ Failed to connect to Upstox WebSocket: {}", e.getMessage());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false; // Upstox WebSocket does not support partial messages
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("🔗 WebSocket connection established.");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try {
            log.info("📊 Received data: {}", message.getPayload());
            MarketData marketData = parseMarketData(message.getPayload().toString());
            marketDataProcessorService.processMarketData(marketData);
        } catch (Exception e) {
            log.error("❌ Error processing WebSocket message: {}", e.getMessage());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("❌ WebSocket Error: {}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.warn("🔌 WebSocket connection closed: {}", status);
        reconnect();
    }

    private void reconnect() {
        try {
            log.info("♻️ Reconnecting to Upstox WebSocket...");
            connect();
        } catch (Exception e) {
            log.error("❌ Reconnection failed: {}", e.getMessage());
        }
    }

    private MarketData parseMarketData(String payload) {
        // Deserialize incoming JSON payload
        return new MarketData(); // Map fields from payload
    }

    public void subscribeToSymbols(List<String> symbols) {
        if (session != null && session.isOpen()) {
            try {
                String subscriptionMessage = buildSubscriptionMessage(symbols);
                session.sendMessage(new TextMessage(subscriptionMessage));
                log.info("📢 Subscribed to symbols: {}", symbols);
            } catch (Exception e) {
                log.error("❌ Subscription failed: {}", e.getMessage());
            }
        }
    }

    private String buildSubscriptionMessage(List<String> symbols) {
        return "{\"action\":\"subscribe\",\"symbols\":" + symbols + "}";
    }
}
