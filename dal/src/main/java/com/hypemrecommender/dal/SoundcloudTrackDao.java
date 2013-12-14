package com.hypemrecommender.dal;

import com.mongodb.DBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 13/12/2013
 * Time: 14:09
 */
public class SoundcloudTrackDao implements TrackDao {
    private final DBCollection trackCollection;
    private final String trackId;

    public SoundcloudTrackDao(final DBCollection trackCollection, final String trackId) {

        this.trackCollection = trackCollection;
        this.trackId = trackId;
    }

    @Override
    public String getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getArtist() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStreamUrl() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCloudId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
