package com.hypemrecommender;

import com.hypemrecommender.representations.HypemUserRepresentation;
import com.sun.jersey.api.client.WebResource;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 05/11/2013
 * Time: 23:01
 */
public class HypemApi implements MusicCloudApi {

    private final WebResource userResource;

    public HypemApi(final WebResource userResource) {
        this.userResource = userResource;
    }

    @Override
    public HypemUserRepresentation fetchUser(final String username) {
        return userResource.queryParam("username", username).get(HypemUserRepresentation.class);
    }

    @Override
    public Collection<CloudTrack> fetchFavourites(final String unCrawledUser) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
