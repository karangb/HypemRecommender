package com.hypemrecommender.models;


import com.hypemrecommender.dal.UserDaoRepository;
import com.hypemrecommender.dal.UserDao;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:26
 */
public class UserImplRepositoryTest {

    private UserImplRepository userFactory;
    private UserDaoRepository userDaoRepository;

    @Before
    public void setUp()
    {
        userDaoRepository = mock(UserDaoRepository.class);
        userFactory = new UserImplRepository(userDaoRepository);
    }

    @Test
    public void testCreateUser()
    {
        User user = userFactory.createUser("karan");

        verify(userDaoRepository).create("karan");
        assertThat(user, instanceOf(UserImpl.class));
    }
}
