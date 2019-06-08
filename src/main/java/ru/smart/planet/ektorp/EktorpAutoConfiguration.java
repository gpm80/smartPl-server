package ru.smart.planet.ektorp;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * Created by Petr Gusarov on 04.02.19.
 */
@Configuration
@EnableConfigurationProperties(EktorpProperties.class)
public class EktorpAutoConfiguration {

    private EktorpProperties ektorpProperties;

    public EktorpAutoConfiguration(final EktorpProperties ektorpProperties) {
        this.ektorpProperties = ektorpProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpClient httpClient() {
        Assert.notNull(ektorpProperties.getClient().getUrl(), "CouchDB URL must not be null");
        Assert.hasLength(ektorpProperties.getDefaultDatabase(), "Default database name must not be empty");

        return new StdHttpClient.Builder()
            .url(ektorpProperties.getClient().getUrl())
            .username(ektorpProperties.getClient().getUsername())
            .password(ektorpProperties.getClient().getPassword())
            .maxConnections(ektorpProperties.getClient().getMaxConnections())
            .connectionTimeout(ektorpProperties.getClient().getConnectionTimeout())
            .socketTimeout(ektorpProperties.getClient().getSocketTimeout())
            .caching(ektorpProperties.getClient().isChaching())
            .maxCacheEntries(ektorpProperties.getClient().getMaxCacheEntries())
            .maxObjectSizeBytes(ektorpProperties.getClient().getMaxObjectSizeBytes())
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CouchDbInstance couchDbInstance() {
        HttpClient httpClient = httpClient();
        return new StdCouchDbInstance(httpClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public CouchDbConnector couchDbConnector() {
        return new StdCouchDbConnector(ektorpProperties.getDefaultDatabase(), couchDbInstance());
    }
}
