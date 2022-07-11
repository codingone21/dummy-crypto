package crypto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import crypto.core.AverageAndStandardDeviation;
import crypto.core.CryptoUtils;

import java.math.RoundingMode;

public class EncryptedStatsResponse {
    private String encryptedAverage;
    private String encryptedStandardDeviation;

    public EncryptedStatsResponse(AverageAndStandardDeviation stats) {
        this.encryptedAverage = CryptoUtils.encrypt(stats.getAverage().setScale(StatsResponse.SCALE, RoundingMode.HALF_UP).toString());
        this.encryptedStandardDeviation = CryptoUtils.encrypt(stats.getStandardDeviation().setScale(StatsResponse.SCALE, RoundingMode.HALF_UP).toString());
    }

    @JsonProperty("average")
    public String getAverage() {
        return this.encryptedAverage;
    }

    @JsonProperty("standardDeviation")
    public String getStandardDeviation() {
        return this.encryptedStandardDeviation;
    }
}
