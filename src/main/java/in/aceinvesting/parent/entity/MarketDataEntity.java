package in.aceinvesting.parent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author: Kush Gudhka
 */
@Entity
@Table(name = "market_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "symbol", nullable = false, unique = true)
    private String symbol; // e.g., "NSE_EQ|RELIANCE"

    @Column(name = "open_price")
    private BigDecimal openPrice;

    @Column(name = "close_price")
    private BigDecimal closePrice;

    @Column(name = "day_high")
    private BigDecimal dayHigh;

    @Column(name = "day_low")
    private BigDecimal dayLow;

    @Column(name = "last_updated", nullable = false)
    private Timestamp lastUpdated; // Track when the record was last updated
}
