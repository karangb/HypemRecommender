package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 30/11/2013
 * Time: 14:19
 */
public class SoundcloudUserDaoTest extends MongoFixture{
    private DBCollection userCollection;
    private DBCollection trackCollection;
    private ObjectId existingTrackId;
    private int soundcloudUserId;
    private SoundcloudUserDao userDao;

    @Before
    public void setUp()
    {
        userCollection = testDb.createCollection("users", new BasicDBObject());
        trackCollection = testDb.createCollection("tracks", new BasicDBObject());

        soundcloudUserId = 6266041;
        BasicDBObject userDoc = new BasicDBObject("soundcloudId", soundcloudUserId).append("favourites", new ArrayList<BasicDBObject>());
        userCollection.insert(userDoc);

        BasicDBObject existingTrackDoc = new BasicDBObject("soundcloudId", "1234");
        trackCollection.insert(existingTrackDoc);
        existingTrackId = (ObjectId) existingTrackDoc.get("_id");
        userDao = new SoundcloudUserDao(userCollection, trackCollection, soundcloudUserId);
    }

    @Test
    public void testProvisionFavouriteLinksUserFavourites()
    {
        CloudTrack existingTrack = new FakeCloudTrack("1234", "myArtist", "myTitle", "http://someUrl");
        userDao.provisionFavourite(existingTrack);

        DBObject myUser = userCollection.findOne(new BasicDBObject("soundcloudId", soundcloudUserId));
        ArrayList<ObjectId> favourites = (ArrayList<ObjectId>) myUser.get("favourites");

        assertThat(favourites.get(0), equalTo(existingTrackId));
    }

    @Test
    public void testProvisionFavouriteProvisionsNewTrack()
    {
        final CloudTrack newTrack = new FakeCloudTrack("456", "myNewArtist", "myNewTitle", "http://someUrl");
        userDao.provisionFavourite(newTrack);
        DBObject track = trackCollection.findOne(new BasicDBObject("soundcloudId", "456"));

        assertThat((String) track.get("artist"), equalTo("myNewArtist"));
        assertThat((String) track.get("title"), equalTo("myNewTitle"));
        assertThat((String) track.get("streamUrl"), equalTo("http://someUrl"));
    }
}
