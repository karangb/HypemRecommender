package com.hypemrecommender.dal;

import com.hypemrecommender.representations.Track;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import junit.framework.Assert;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 13/09/2013
 * Time: 18:57
 */
public class TrackDaoTest extends MongoFixture{
    @Test
    public void testGetTrack() throws UnknownHostException {
        Track track = new Track("RÜFÜS (official)", "Desert Night");
        provision(track);
        TrackDao trackDao = new MongoTrackDao("127.0.0.1", "test");
        Assert.assertEquals(track, trackDao.getTrack(1));
    }

    private void provision(final Track track) throws UnknownHostException {
        DBCollection tracks = testDb.createCollection("tracks", new BasicDBObject());
        BasicDBObject trackDoc = new BasicDBObject("media_id", "1x990").append("artist", track.getArtist()).append("title", track.getTitle());
        tracks.insert(trackDoc);

        ObjectId id = (ObjectId)trackDoc.get( "_id" );
        DBCollection dataModelMap = testDb.createCollection("mongo_data_model_map", new BasicDBObject());
        dataModelMap.insert(new BasicDBObject("element_id", id).append("long_value", 1));
    }
}