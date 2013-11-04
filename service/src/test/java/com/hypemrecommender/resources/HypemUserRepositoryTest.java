package com.hypemrecommender.resources;


import com.hypemrecommender.Crawler;
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
public class HypemUserRepositoryTest {

    private HypemUserRepository userRepository;
    private UserDao userDao;
    private Crawler crawler;

    @Before
    public void setUp()
    {
        userDao = mock(UserDao.class);
        crawler = mock(Crawler.class);
        userRepository = new HypemUserRepository(userDao, crawler);
    }

    @Test
    public void testGetUser()
    {
        long hypemId = 123;
        when(userDao.getUserId("karan")).thenReturn(hypemId);

        User user = userRepository.getUser("karan");
        assertEquals(user.getId(), 123);
    }
}
