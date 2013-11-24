package com.hypemrecommender.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 22/11/2013
 * Time: 22:39
 */
public class SoundcloudUserFactoryTest {
    @Test
    public void testSoundcloudUserCreated()
    {
        SoundcloudUserRepository factory = new SoundcloudUserRepository();
        User user = factory.createUser("karan");
        assertThat(user, instanceOf(SoundcloudUser.class));
    }
}
