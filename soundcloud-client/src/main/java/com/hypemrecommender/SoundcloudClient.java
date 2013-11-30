package com.hypemrecommender;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.representations.SoundcloudTrack;

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

    private final SoundcloudResource userResource;
    private final String clientId;

    public SoundcloudClient(final String clientId) {
        this.clientId = clientId;
        userResource = new SoundcloudResource("http://api.soundcloud.com/users", clientId);
    }

    @Override
    public Collection<CloudTrack> fetchFavourites(final String userId) throws IOException {
        SoundcloudResource favourites = userResource.path(String.format("%s/favorites.json", userId));

        SoundcloudTrack[] tracks = favourites.get(SoundcloudTrack[].class);
        SoundcloudResource trackResource = new SoundcloudResource("http://api.soundcloud.com/tracks", clientId);

        for(SoundcloudTrack track : tracks)
        {
            track.setTrackResource(trackResource);
        }

        return Arrays.asList((CloudTrack[])tracks);
    }
}