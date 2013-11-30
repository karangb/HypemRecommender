package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 30/11/2013
 * Time: 22:36
 */
public class FakeCloudTrack implements CloudTrack {
    private final String id;
    private final String artist;
    private final String title;
    private final String streamUrl;

    public FakeCloudTrack(final String id, final String artist, final String title, final String streamUrl) {

        this.id = id;
        this.artist = artist;
        this.title = title;
        this.streamUrl = streamUrl;
    }

    @Override
    public Collection<String> getFavouriters() {
        return null;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getStreamUrl() {
        return streamUrl;
    }
}
