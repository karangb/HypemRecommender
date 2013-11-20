package com.hypemrecommender.resources;

import com.hypemrecommender.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.models.HypemUser;
import com.hypemrecommender.models.User;
import com.hypemrecommender.representations.HypemUserRepresentation;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/11/2013
 * Time: 19:34
 */
public class HypemUserTest {

    @Test
    public void testRefreshWhenUserDoesNotExist()
    {
        UserDao userDao = mock(UserDao.class);
        MusicCloudApi musicCloudApi = mock(MusicCloudApi.class);
        final HypemUserRepresentation hypemUser = new HypemUserRepresentation();
        when(userDao.exists("karan")).thenReturn(false);
        when(musicCloudApi.fetchUser("karan")).thenReturn(hypemUser);

        User user = new HypemUser("karan", userDao, musicCloudApi);
        user.updateFavourites();

        verify(userDao).provision(hypemUser);
    }
}
