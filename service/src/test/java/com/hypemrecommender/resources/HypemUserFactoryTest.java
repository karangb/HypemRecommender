package com.hypemrecommender.resources;


import com.hypemrecommender.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.models.HypemUserFactory;
import com.hypemrecommender.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:26
 */
public class HypemUserFactoryTest {

    private HypemUserFactory userRepository;
    private UserDao userDao;
    private MusicCloudApi musicCloudApi;

    @Before
    public void setUp()
    {
        userDao = mock(UserDao.class);
        musicCloudApi = mock(MusicCloudApi.class);
        userRepository = new HypemUserFactory(userDao, musicCloudApi);
    }

    @Test
    public void testGetUser()
    {
        long hypemId = 123;
        when(userDao.getUserId("karan")).thenReturn(hypemId);

        User user = userRepository.createUser("karan");
        assertEquals(user.getId(), 123);
    }
}
