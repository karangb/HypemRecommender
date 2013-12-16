package com.hypemrecommender.dal;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/12/2013
 * Time: 22:03
 */
public class SoundcloudTrackDaoTest extends MongoFixture{

    private CloudId cloudId;
    private SoundcloudTrackDao soundcloudTrackDao;
    private TrackDBObject trackDoc;

    @Before
    public void setUp()
    {
        cloudId = new CloudId(123);
        trackDoc = new TrackDBObject(cloudId, "myTitle", "myArtist", "myStreamUrl");
        trackCollection.insert(trackDoc);
        soundcloudTrackDao = new SoundcloudTrackDao(trackCollection, cloudId);
    }

    @Test
    public void testGetId()
    {
        assertThat(soundcloudTrackDao.getId(), equalTo(trackDoc.get("_id").toString()));
    }

    @Test
    public void testGetCloudId()
    {
        assertThat(soundcloudTrackDao.getCloudId(), equalTo("123"));
    }

    @Test
    public void testGetTitle()
    {
        assertThat(soundcloudTrackDao.getTitle(), equalTo("myTitle"));
    }

    @Test
    public void testGetArtist()
    {
        assertThat(soundcloudTrackDao.getArtist(), equalTo("myArtist"));
    }

    @Test
    public void testGetStreamUrl()
    {
        assertThat(soundcloudTrackDao.getStreamUrl(), equalTo("myStreamUrl"));
    }
}
