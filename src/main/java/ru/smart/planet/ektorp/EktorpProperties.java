package ru.smart.planet.ektorp;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URL;

/**
 * Created by Petr Gusarov on 04.02.19.
 */
@ConfigurationProperties(prefix = "spring.ektorp")
public class EktorpProperties {

    private ClientProperties client = new ClientProperties();

    private String defaultDatabase = "db";

    public ClientProperties getClient() {
        return client;
    }

    public void setClient(ClientProperties clientProperties) {
        this.client = clientProperties;
    }

    public String getDefaultDatabase() {
        return defaultDatabase;
    }

    public void setDefaultDatabase(String defaultDatabase) {
        this.defaultDatabase = defaultDatabase;
    }

    public static class ClientProperties {

        private URL url;

        private String username;

        private String password;

        private int maxConnections = 20;

        private int connectionTimeout = 1000;

        private int socketTimeout = 10000;

        private boolean chaching = true;

        private int maxCacheEntries = 1000;

        private int maxObjectSizeBytes = 8192;

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getMaxConnections() {
            return maxConnections;
        }

        public void setMaxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
        }

        public int getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public int getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public boolean isChaching() {
            return chaching;
        }

        public void setChaching(boolean chaching) {
            this.chaching = chaching;
        }

        public int getMaxCacheEntries() {
            return maxCacheEntries;
        }

        public void setMaxCacheEntries(int maxCacheEntries) {
            this.maxCacheEntries = maxCacheEntries;
        }

        public int getMaxObjectSizeBytes() {
            return maxObjectSizeBytes;
        }

        public void setMaxObjectSizeBytes(int maxObjectSizeBytes) {
            this.maxObjectSizeBytes = maxObjectSizeBytes;
        }

    }
}
