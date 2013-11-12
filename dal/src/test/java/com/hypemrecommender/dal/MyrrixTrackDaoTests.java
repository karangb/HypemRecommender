package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 16:05
 */
public class MyrrixTrackDaoTests extends MongoFixture{
    private DBCollection trackCollection;
    private MyrrixTrackDao trackDao;

    @Before
    public void setUp() throws UnknownHostException {
        trackCollection = testDb.createCollection("tracks", new BasicDBObject());

        trackDao = new MyrrixTrackDao(trackCollection);
    }

    @Test
    public void testProvision()
    {
        HypemTrackRepresentation track = FakeRepresentations.track1();
        trackDao.provision(track);

        DBObject trackDoc = trackCollection.findOne(new BasicDBObject("media_id", track.getMediaId()));
        trackDoc.removeField("_id");
        assertThat(trackDoc, equalTo(FakeRepresentations.track1Doc()));
    }
}
