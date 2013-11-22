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
        SoundcloudUserFactory factory = new SoundcloudUserFactory();
        User user = factory.createUser("karan");
        assertThat(user, instanceOf(SoundcloudUser.class));
    }
}
