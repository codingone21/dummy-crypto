package crypto.core;

import crypto.api.StatsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class AverageAndStandardDeviationTest {
    AverageAndStandardDeviation averageAndStandardDeviation;

    @BeforeEach
    void setUp() {
        averageAndStandardDeviation = new AverageAndStandardDeviation();
    }

    @Test
    void getAverage_whenInitialized_returnsZero() {
        Assertions.assertEquals(BigDecimal.ZERO, averageAndStandardDeviation.getAverage());
    }

    @Test
    void getStandardDeviation_whenInitialized_returnsZero() {
        Assertions.assertEquals(BigDecimal.ZERO, averageAndStandardDeviation.getStandardDeviation());
    }

    @Test
    void updateRunningStats_whenGivenNumbers_recalculatesStats() {
        BigDecimal[] samples = {BigDecimal.valueOf(4.0), BigDecimal.valueOf(7.0), BigDecimal.valueOf(6.0)};
        BigDecimal[] expectedAverages = {BigDecimal.valueOf(4.0), BigDecimal.valueOf(5.5), BigDecimal.valueOf(5.667)};
        BigDecimal[] expectedStandardDeviations = {BigDecimal.valueOf(0), BigDecimal.valueOf(1.5), BigDecimal.valueOf(1.247)};
        for (int i = 0; i < samples.length; i++) {
            averageAndStandardDeviation.updateRunningStats(samples[i]);
            Assertions.assertEquals(
                    expectedAverages[i].setScale(StatsResponse.SCALE, RoundingMode.HALF_UP),
                    averageAndStandardDeviation.getAverage().setScale(StatsResponse.SCALE, RoundingMode.HALF_UP)
            );
            Assertions.assertEquals(
                    expectedStandardDeviations[i].setScale(StatsResponse.SCALE, RoundingMode.HALF_UP),
                    averageAndStandardDeviation.getStandardDeviation().setScale(StatsResponse.SCALE, RoundingMode.HALF_UP)
            );
        }
    }

    @AfterEach
    void tearDown() {
        averageAndStandardDeviation = null;
    }
}