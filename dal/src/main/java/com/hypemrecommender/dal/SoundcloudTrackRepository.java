package com.hypemrecommender.dal;

import com.mongodb.DBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/12/2013
 * Time: 20:26
 */
public class SoundcloudTrackRepository implements TrackDaoRepository{

    private final DBCollection trackCollection;

    public SoundcloudTrackRepository(final DBCollection trackCollection) {
        this.trackCollection = trackCollection;
    }

    @Override
    public TrackDao get(final String trackId) {
        return new SoundcloudTrackDao(trackCollection, trackId);
    }

    @Override
    public TrackDao get(final CloudId cloudTrackId) {
        return new SoundcloudTrackDao(trackCollection, cloudTrackId);
    }
}
