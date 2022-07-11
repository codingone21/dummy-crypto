package crypto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

public class CryptoServiceConfiguration extends Configuration {
    @NotEmpty
    private String description;

    @NotEmpty
    private String creator;

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public String getCreator() {
        return creator;
    }

    @JsonProperty
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
