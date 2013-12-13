package com.hypemrecommender.models;


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

    @Before
    public void setUp()
    {
        userDaoRepository = mock(UserDaoRepository.class);
        userRepository = new UserImplRepository(userDaoRepository, null);
    }

    @Test
    public void testCreateUser()
    {
        User user = userRepository.createUser("karan");

        verify(userDaoRepository).createUserDao("karan");
        assertThat(user, instanceOf(UserImpl.class));
    }

    @Test
    public void testExists()
    {
        when(userDaoRepository.userExists("123456")).thenReturn(true);
        assertTrue(userRepository.exists("123456"));
        assertFalse(userRepository.exists("789"));
    }
}
