package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:06
 */
public class SoundcloudRepositoryTest extends MongoFixture{

    private SoundcloudRepository repository;

    @Before
    public void setUp()
    {
        repository = new SoundcloudRepository(userCollection, trackCollection);
    }

    @Test
    public void testSoundcloudUserDaoCreated()
    {
        UserDao userDao = repository.create("123456");
        assertThat(userDao, instanceOf(SoundcloudUserDao.class));
    }

    @Test
    public void testExists()
    {
        userCollection.insert(new BasicDBObject("soundcloudId", "123456"));
        assertTrue(repository.userExists("123456"));
    }
}
