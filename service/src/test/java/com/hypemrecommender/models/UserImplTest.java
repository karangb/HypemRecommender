package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 26/11/2013
 * Time: 23:14
 */
public class UserImplTest {
    @Mock UserDao userDao;
    @Mock MusicCloudApi musicCloudApi;
    @Mock CloudTrack track1;
    @Mock CloudTrack track2;
    private ArrayList<CloudTrack> favourites;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        favourites = new ArrayList<>();
        favourites.add(track1);
        favourites.add(track2);
    }
    @Test
    public void testAddFavourites() throws IOException {
        UserImpl user = new UserImpl(userDao);
        user.addFavourites(favourites);

        verify(userDao).provisionFavourite(track1);
        verify(userDao).provisionFavourite(track2);
    }

}
