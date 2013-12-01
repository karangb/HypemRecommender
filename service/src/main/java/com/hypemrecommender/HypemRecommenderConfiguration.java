package com.hypemrecommender;

import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/09/2013
 * Time: 22:51
 */
class HypemRecommenderConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    public String host;

    @NotEmpty
    @JsonProperty
    public String db;

    @JsonProperty
    public List<String> allowedOrigins;

    @NotEmpty
    @JsonProperty
    public String soundcloudKey;

    @NotEmpty
    @JsonProperty
    public String oryxUrl;

    public String getHost()
    {
        return host;
    }

    public String getDb()
    {
        return db;
    }

    public List<String> getAllowedOrigins()
    {
        return allowedOrigins;
    }

    public String getSoundcloudKey() {
        return soundcloudKey;
    }


    public String getOryxUrl() {
        return oryxUrl;
    }
}
