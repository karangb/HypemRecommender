package com.hypemrecommender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 25/11/2013
 * Time: 22:34
 */
public class SoundcloudResource {
    private final String clientId;
    private WebResource webResource;

    public SoundcloudResource(final String url, final String clientId) {
        final Client client = Client.create();
        webResource = client.resource(url);
        this.clientId = clientId;
    }

    public SoundcloudResource path(final String path) {
        webResource = webResource.path(path);
        return this;
    }

    public <T> T get(final Class<T> aClass) {
        return webResource.queryParam("client_id", clientId).accept(MediaType.APPLICATION_JSON_TYPE).get(aClass);
    }
}
