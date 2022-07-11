package crypto.health;

import com.codahale.metrics.health.HealthCheck;

public class DescriptionHealthCheck extends HealthCheck {
    private final String description;

    public DescriptionHealthCheck(String template) {
        this.description = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(description, "TESTER");
        if (!saying.contains("TESTER")) {
            return Result.unhealthy("description doesn't include a creator");
        }
        return Result.healthy();
    }
}
