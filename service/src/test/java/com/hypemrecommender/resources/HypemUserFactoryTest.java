package com.hypemrecommender.resources;


import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.models.HypemUserRepository;
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

    private HypemUserRepository userFactory;
    private UserDao userDao;
    private MusicCloudApi musicCloudApi;

    @Before
    public void setUp()
    {
        userDao = mock(UserDao.class);
        musicCloudApi = mock(MusicCloudApi.class);
        userFactory = new HypemUserRepository(userDao, musicCloudApi);
    }

    @Test
    public void testCreateUser()
    {
        long hypemId = 123;
        when(userDao.getUserId("karan")).thenReturn(hypemId);

        User user = userFactory.createUser("karan");
        assertEquals(user.getId(), 123);
    }
}
