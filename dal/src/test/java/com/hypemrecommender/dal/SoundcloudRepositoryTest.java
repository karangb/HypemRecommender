package com.hypemrecommender.dal;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:06
 */
public class SoundcloudRepositoryTest {
    @Test
    public void testSoundcloudUserDaoCreated()
    {
        SoundcloudRepository repository = new SoundcloudRepository();
        UserDao userDao = repository.create("karan");
        assertThat(userDao, instanceOf(SoundcloudUserDao.class));
    }
}
