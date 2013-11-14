package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static com.hypemrecommender.dal.FakeRepresentations.track1;
import static com.hypemrecommender.dal.FakeRepresentations.track1Doc;
import static com.mongodb.util.MyAsserts.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 16:05
 */
public class MyrrixTrackDaoTest extends MongoFixture{
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
        HypemTrackRepresentation track = track1();
        trackDao.provision(track);

        DBObject trackDoc = trackCollection.findOne(new BasicDBObject("media_id", track.getMediaId()));
        trackDoc.removeField("_id");
        assertThat(trackDoc, equalTo(track1Doc()));
    }

    @Test
    public void testExist()
    {
        HypemTrackRepresentation track = track1();

        trackDao.provision(track);

        assertTrue(trackDao.exists(track));
    }

    @Test
    public void testExistWithoutTrack()
    {
        assertFalse(trackDao.exists(track1()));
    }
}
