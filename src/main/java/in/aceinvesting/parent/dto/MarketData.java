package in.aceinvesting.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: Kush Gudhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketData {
    private String symbol;       // e.g., "NSE_EQ|RELIANCE"
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;
}