package com.hypemrecommender;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.representations.SoundcloudTrack;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/*
 Created with IntelliJ IDEA.
 User: @karangb
 Date: 24/11/2013
 Time: 02:51
 */
public class SoundcloudClient implements MusicCloudApi {

    private final WebResource userResource;
    private final String clientId;

    public SoundcloudClient(final String clientId) {
        this.clientId = clientId;
        final Client client = Client.create();
        userResource = client.resource("http://api.soundcloud.com/users");
    }

    @Override
    public Collection<CloudTrack> fetchFavourites(final String userId) throws IOException {
        WebResource favourites = userResource.path(String.format("%s/favorites.json", userId));
        CloudTrack[] tracks = favourites.queryParam("client_id", clientId).accept(MediaType.APPLICATION_JSON_TYPE).get(SoundcloudTrack[].class);
        return Arrays.asList(tracks);
    }
}