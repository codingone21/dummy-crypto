package crypto.resources;

import crypto.api.EncryptedStatsResponse;
import crypto.api.StatsResponse;
import crypto.core.CryptoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

class DummyCryptoResourceTest {
    String description = "description %s";
    String creator = "creator";
    DummyCryptoResource dummyCryptoResource = new DummyCryptoResource(description, creator);

    @Test
    void getInfo_returnsInfoText() {
        Assertions.assertEquals(String.format(description, creator), dummyCryptoResource.getInfo());
    }

    @Test
    void pushAndRecalculate_returnsExpectedStats() {
        Long[] samples = {4L, 7L, 6L, -100L};
        BigDecimal[] expectedAverages = {BigDecimal.valueOf(4.0), BigDecimal.valueOf(5.5), BigDecimal.valueOf(5.667), BigDecimal.valueOf(-20.75)};
        BigDecimal[] expectedStandardDeviations = {BigDecimal.valueOf(0), BigDecimal.valueOf(1.5), BigDecimal.valueOf(1.247), BigDecimal.valueOf(45.768)};
        for (int i = 0; i < samples.length; i++) {
            StatsResponse response = dummyCryptoResource.pushAndRecalculate(samples[i]);
            Assertions.assertEquals(
                    expectedAverages[i].setScale(StatsResponse.SCALE, RoundingMode.HALF_UP),
                    response.getAverage()
            );
            Assertions.assertEquals(
                    expectedStandardDeviations[i].setScale(StatsResponse.SCALE, RoundingMode.HALF_UP),
                    response.getStandardDeviation()
            );
        }
    }

    @Test
    void pushAndRecalculateAndEncrypt_returnsExpectedStats() {
        Long[] samples = {4L, 7L, 6L};
        String[] expectedAverages = {"4.000", "5.500", "5.667"};
        String[] expectedStandardDeviations = {"0.000", "1.500", "1.247"};
        for (int i = 0; i < samples.length; i++) {
            EncryptedStatsResponse encryptedResponse = dummyCryptoResource.pushAndRecalculateAndEncrypt(samples[i]);
            Assertions.assertEquals(
                    expectedAverages[i],
                    CryptoUtils.decrypt(encryptedResponse.getAverage())
            );
            Assertions.assertEquals(
                    expectedStandardDeviations[i],
                    CryptoUtils.decrypt(encryptedResponse.getStandardDeviation())
            );
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"6.5", "1.0", "0.0", "8", "100", "-1.5", "-100"})
    void decrypt_returnsTheSameSource(String source) {
        String encrypted = CryptoUtils.encrypt(source);
        Assertions.assertEquals(source, dummyCryptoResource.decrypt(encrypted));
    }
}