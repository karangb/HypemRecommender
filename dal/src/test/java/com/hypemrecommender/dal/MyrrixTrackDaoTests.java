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
        HypemTrackRepresentation track = new HypemTrackRepresentation("1a13f", "Parov Stelar", "The Golden Boy", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");
        trackDao.provision(track);

        assertTrackProvisioned(track);
    }

    private void assertTrackProvisioned(final HypemTrackRepresentation track) {
        DBObject trackDoc = trackCollection.findOne(new BasicDBObject("media_id", track.getMediaId()));
        assertThat((String)trackDoc.get("artist"), equalTo(track.getArtist()));
        assertThat((String)trackDoc.get("title"), equalTo(track.getTitle()));
        assertThat((String)trackDoc.get("thumb_url"), equalTo(track.getThumbUrl()));
        assertThat((String)trackDoc.get("thumb_url_medium"), equalTo(track.getThumbUrlMedium()));
        assertThat((String)trackDoc.get("thumb_url_large"), equalTo(track.getThumbUrlLarge()));
    }
}
