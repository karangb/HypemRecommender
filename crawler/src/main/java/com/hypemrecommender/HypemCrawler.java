package com.hypemrecommender;

import com.hypemrecommender.representations.HypemUserRepresentation;
import com.sun.jersey.api.client.WebResource;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 05/11/2013
 * Time: 23:01
 */
public class HypemCrawler implements Crawler {

    private final WebResource userResource;

    public HypemCrawler(final WebResource userResource) {
        this.userResource = userResource;
    }

    @Override
    public HypemUserRepresentation fetchUser(final String username) {
        return userResource.queryParam("username", username).get(HypemUserRepresentation.class);
    }
}
