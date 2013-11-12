package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.Track;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 17:25
 */
public class MyrrixTrackDao implements TrackDao{
    private final DBCollection trackCollection;

    public MyrrixTrackDao(final DBCollection trackCollection) {
        this.trackCollection = trackCollection;
    }

    @Override
    public Track getTrack(final long itemID) {
        return null;
    }

    @Override
    public boolean exists(final HypemTrackRepresentation trackRepresentation) {
        return trackCollection.find(new BasicDBObject("media_id", trackRepresentation.getMediaId())).limit(1).count() != 0;
    }

    public void provision(final HypemTrackRepresentation track) {
        BasicDBObjectFactory factory = new BasicDBObjectFactory();
        trackCollection.insert(factory.createTrack(track));
    }
}
