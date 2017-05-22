package com.digitalinside.perf.app;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Logger;

public class JerseyClientBuilder {
    public static Client getClient() {
        return getClientWithLogging();
    }

    public static Client getAnonymousClient() {
        return getClientWithLogging();
    }

    private static Client getClientWithLogging() {
        final Client client = ClientBuilder.newClient(new ClientConfig());
        return client.register(new LoggingFilter(
                Logger.getLogger(JerseyClientBuilder.class.getName()),
                true));
    }
}
