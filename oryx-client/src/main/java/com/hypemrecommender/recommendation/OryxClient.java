package com.hypemrecommender.recommendation;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 01/12/2013
 * Time: 13:53
 */
public class OryxClient implements RecommendationClient{
    private final WebResource oryx;

    public OryxClient(String url)
    {
        final Client client = Client.create();
        oryx = client.resource(url);
    }

    @Override
    public String getTopRecommendation(final String userId) {
        String result = oryx.path(String.format("recommend/%s", userId)).accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        return result.split(",")[0];
    }

    @Override
    public void pref(final String userId, final String itemId, final int rating) {
        oryx.path(String.format("pref/%s/%s", userId, itemId)).post(String.valueOf(rating));
    }
}
