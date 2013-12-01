package com.hypemrecommender.dal;

import com.hypemrecommender.representations.TrackRepresentation;
import com.mongodb.BasicDBObject;
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
public class MahoutTrackDaoTest extends MongoFixture{
    @Test
    public void testGetTrack() throws UnknownHostException {
        TrackRepresentation trackRepresentation = new TrackRepresentation("1x990", "RÜFÜS (official)", "Desert Night");
        provision(trackRepresentation);
        TrackDao trackDao = new MahoutTrackDao(dataModelMap, trackCollection);
        Assert.assertEquals(trackRepresentation, trackDao.getTrack(1));
    }

    private void provision(final TrackRepresentation trackRepresentation) {
        BasicDBObject trackDoc = new BasicDBObject("media_id", "1x990").append("artist", trackRepresentation.getArtist()).append("title", trackRepresentation.getTitle());
        trackCollection.insert(trackDoc);
        ObjectId id = (ObjectId)trackDoc.get( "_id" );
        dataModelMap.insert(new BasicDBObject("element_id", id.toString()).append("long_value", "1"));
    }
}
