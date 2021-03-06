package com.hypemrecommender.models;


import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.dal.UserDaoRepository;
import org.junit.Before;
import org.junit.Test;

import static com.mongodb.util.MyAsserts.assertFalse;
import static com.mongodb.util.MyAsserts.assertTrue;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:26
 */
public class UserImplRepositoryTest {

    private UserImplRepository userRepository;
    private UserDaoRepository userDaoRepository;
    private UserDao userDao;

    @Before
    public void setUp()
    {
        userDao = mock(UserDao.class);
        userDaoRepository = mock(UserDaoRepository.class);
        userRepository = new UserImplRepository(userDaoRepository, null, null, "clientId");
    }

    @Test
    public void testCreateUser()
    {
        CloudId userId = new CloudId(123);
        User user = userRepository.createUser(userId);

        verify(userDaoRepository).createUserDao(userId);
        assertThat(user, instanceOf(UserImpl.class));
    }

    @Test
    public void testExists()
    {
        CloudId userId = new CloudId(123456);
        when(userDaoRepository.userExists(userId)).thenReturn(true);

        assertTrue(userRepository.exists(userId));
        assertFalse(userRepository.exists(new CloudId(0)));
    }

    @Test
    public void testGetUser()
    {
        CloudId userId = new CloudId(1234);
        when(userDaoRepository.get(userId)).thenReturn(userDao);

        User user = userRepository.getUser(userId);

        assertThat(user, instanceOf(UserImpl.class));
        verify(userDaoRepository).get(userId);
    }
}
