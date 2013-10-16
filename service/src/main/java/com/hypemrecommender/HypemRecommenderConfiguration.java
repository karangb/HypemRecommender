package com.hypemrecommender;

import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

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

    public String getHost()
    {
        return host;
    }

    public String getDb()
    {
        return db;
    }
}
