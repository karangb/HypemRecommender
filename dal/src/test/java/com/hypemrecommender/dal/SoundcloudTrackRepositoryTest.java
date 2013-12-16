package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/12/2013
 * Time: 17:20
 */
public class SoundcloudTrackRepositoryTest extends MongoFixture{

    @Test
    public void testGetWithCloudId()
    {
        trackCollection.insert(new SoundcloudDBObject(1234));

        SoundcloudTrackRepository repository = new SoundcloudTrackRepository(trackCollection);
        TrackDao actual = repository.get(new CloudId(1234));
        assertThat(actual, instanceOf(SoundcloudTrackDao.class));
        assertThat(actual.getCloudId(), equalTo("1234"));
    }

    @Test
    public void testGetWithTrackId()
    {
        BasicDBObject trackDoc = new BasicDBObject();
        trackCollection.insert(trackDoc);

        SoundcloudTrackRepository repository = new SoundcloudTrackRepository(trackCollection);
        assertThat(repository.get(trackDoc.get("_id").toString()), instanceOf(SoundcloudTrackDao.class));
    }
}
