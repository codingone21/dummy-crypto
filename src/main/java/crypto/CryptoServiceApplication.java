package crypto;

import crypto.health.DescriptionHealthCheck;
import crypto.resources.DummyCryptoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CryptoServiceApplication extends Application<CryptoServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CryptoServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "CryptoService";
    }

    @Override
    public void initialize(final Bootstrap<CryptoServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CryptoServiceConfiguration configuration,
                    final Environment environment) {
        environment.healthChecks().register("description", new DescriptionHealthCheck(configuration.getDescription()));
        environment.jersey().register(new DummyCryptoResource(configuration.getDescription(), configuration.getCreator()));
    }

}
