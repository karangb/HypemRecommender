package com.hypemrecommender.recommendation;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

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
    public void pref(final String userId, final String itemId, final int rating) {
        oryx.path(String.format("pref/%s/%s", userId, itemId)).post(String.valueOf(rating));
    }
}
