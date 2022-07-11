package crypto.resources;

import crypto.api.EncryptedStatsResponse;
import crypto.api.StatsResponse;
import crypto.core.AverageAndStandardDeviation;
import crypto.core.CryptoUtils;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("crypto")
@Produces(MediaType.APPLICATION_JSON)
public class DummyCryptoResource {
    private final String description;
    private final String creator;
    public AverageAndStandardDeviation averageAndStandardDeviation = new AverageAndStandardDeviation();

    public DummyCryptoResource(String description, String creator) {
        this.description = description;
        this.creator = creator;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
        return String.format(this.description, creator);
    }

    @PUT
    @Path("pushAndRecalculate")
    @Produces(MediaType.APPLICATION_JSON)
    public StatsResponse pushAndRecalculate(Long sample) {
        averageAndStandardDeviation.updateRunningStats(new BigDecimal(sample));
        return new StatsResponse(averageAndStandardDeviation);
    }

    @PUT
    @Path("pushAndRecalculateAndEncrypt")
    @Produces(MediaType.APPLICATION_JSON)
    public EncryptedStatsResponse pushAndRecalculateAndEncrypt(Long sample) {
        averageAndStandardDeviation.updateRunningStats(new BigDecimal(sample));
        return new EncryptedStatsResponse(averageAndStandardDeviation);
    }

    @GET
    @Path("decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String decrypt(String encrypted) {
        return CryptoUtils.decrypt(encrypted);
    }

}
