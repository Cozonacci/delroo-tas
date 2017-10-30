package org.delroo.tas.domain;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import org.delroo.tas.generic.UriResource;

import java.util.Map;

public class TheGuardianConfig implements UriResource {

    private String uri;
    private String apiKey;
    private Map<String, Object> paths;

    public static TheGuardianConfig fromConfig() {
        Config config = ConfigFactory.load();
        return ConfigBeanFactory.create(config.getConfig("app.sut.the-guardian"), TheGuardianConfig.class);
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Map<String, Object> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Object> paths) {
        this.paths = paths;
    }

    public TheGuardianConfig withApiKey(final String apiKey) {
        setApiKey(apiKey);
        return this;
    }
}
