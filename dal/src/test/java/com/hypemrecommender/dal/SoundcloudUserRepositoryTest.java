package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.mongodb.util.MyAsserts.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:06
 */
public class SoundcloudUserRepositoryTest extends MongoFixture{

    private SoundcloudUserRepository repository;

    @Before
    public void setUp()
    {
        repository = new SoundcloudUserRepository(userCollection, trackCollection);
    }

    @Test
    public void testSoundcloudUserDaoCreated()
    {
        UserDao userDao = repository.createUserDao(new CloudId(123456));
        DBObject doc = userCollection.findOne(new BasicDBObject("soundcloudId", 123456));
        List<String> favourites = (List<String>) doc.get("favourites");
        assertTrue(favourites.isEmpty());
        assertThat(userDao, instanceOf(SoundcloudUserDao.class));
    }

    @Test
    public void testCreateTrackDao()
    {
        TrackDao trackDao = repository.createTrackDao(new CloudId(123456), "myTitle", "myArtist", "myStreamUrl");
        DBObject doc = trackCollection.findOne(new BasicDBObject("soundcloudId", 123456));

        assertThat((String)doc.get("title"), equalTo("myTitle"));
        assertThat((String)doc.get("artist"), equalTo("myArtist"));
        assertThat((String)doc.get("streamUrl"), equalTo("myStreamUrl"));
        assertThat(trackDao, instanceOf(SoundcloudTrackDao.class));
    }

    @Test
    public void testUserExists()
    {
        CloudId userId = new CloudId(123456);
        repository.createUserDao(userId);
        assertTrue(repository.userExists(userId));
        assertFalse(repository.userExists(new CloudId(456)));
    }

    @Test
    public void testTrackExists()
    {
        CloudId trackId = new CloudId(123456);
        repository.createTrackDao(trackId, "myTitle", "myArtist", "myStreamUrl");
        assertTrue(repository.trackExists(trackId));
        assertFalse(repository.trackExists(new CloudId(789)));
    }

    @Test
    public void testGet()
    {
        CloudId userId = new CloudId(123456);
        UserDao originalUserDao = repository.createUserDao(userId);
        assertThat(repository.get(userId), equalTo(originalUserDao));
    }
}
