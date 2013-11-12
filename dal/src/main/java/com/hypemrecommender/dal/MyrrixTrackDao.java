package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.mongodb.DBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 17:25
 */
public class MyrrixTrackDao {
    private final DBCollection trackCollection;

    public MyrrixTrackDao(final DBCollection trackCollection) {
        this.trackCollection = trackCollection;
    }

    public void provision(final HypemTrackRepresentation track) {
        BasicDBObjectFactory factory = new BasicDBObjectFactory();
        trackCollection.insert(factory.createTrack(track));
    }
}
