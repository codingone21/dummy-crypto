package crypto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import crypto.core.AverageAndStandardDeviation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatsResponse {
    public static final int SCALE = 3;
    protected BigDecimal average;
    protected BigDecimal standardDeviation;

    public StatsResponse(AverageAndStandardDeviation stats) {
        this.average = stats.getAverage().setScale(SCALE, RoundingMode.HALF_UP);
        this.standardDeviation = stats.getStandardDeviation().setScale(SCALE, RoundingMode.HALF_UP);
    }

    @JsonProperty("average")
    public BigDecimal getAverage() {
        return this.average;
    }

    @JsonProperty("standardDeviation")
    public BigDecimal getStandardDeviation() {
        return this.standardDeviation;
    }
}
