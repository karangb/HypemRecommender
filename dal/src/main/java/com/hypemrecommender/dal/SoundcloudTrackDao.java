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
}
