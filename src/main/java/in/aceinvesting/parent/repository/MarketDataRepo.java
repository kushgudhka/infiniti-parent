package in.aceinvesting.parent.repository;

import in.aceinvesting.parent.entity.MarketDataEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @Author: Kush Gudhka
 */
@Repository
public interface MarketDataRepo extends JpaRepository<MarketDataEntity, Long> {
    Optional<MarketDataEntity> findBySymbol(String symbol);

    @Transactional
    @Modifying
    @Query("DELETE FROM MarketDataEntity m " +
            "WHERE m.lastUpdated BETWEEN :start AND :end " +
            "AND m.lastUpdated NOT IN (:morning, :closing)")
    int deleteBetweenTimes(@Param("start") LocalDateTime start,
                           @Param("end") LocalDateTime end,
                           @Param("morning") LocalDateTime morning,
                           @Param("closing") LocalDateTime closing);
}