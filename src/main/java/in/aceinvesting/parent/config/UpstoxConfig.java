package in.aceinvesting.parent.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Kush Gudhka
 */
@Getter
@Configuration
public class UpstoxConfig {
    @Value("${upstox.api.key}")
    private String apiKey;

    @Value("${upstox.api.secret}")
    private String apiSecret;

    @Value("${upstox.webSocket.url}")
    private String webSocketUrl;

    @Value("${upstox.access.token}")
    private String accessToken;
}