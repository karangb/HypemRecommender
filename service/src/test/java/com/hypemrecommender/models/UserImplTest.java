package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.recommendation.RecommendationClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Mock RecommendationClient recommendationClient;
    private ArrayList<CloudTrack> favourites;



    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        favourites = new ArrayList<>();
        favourites.add(track1);
        favourites.add(track2);

        when(userDao.getId()).thenReturn("userDao123");
        when(userDao.provisionFavourite(track1)).thenReturn("track123");
        when(userDao.provisionFavourite(track2)).thenReturn("track456");
    }
    @Test
    public void testAddFavourites() throws IOException {
        UserImpl user = new UserImpl(userDao, recommendationClient);
        user.addFavourites(favourites);

        verify(userDao).provisionFavourite(track1);
        verify(userDao).provisionFavourite(track2);
        verify(recommendationClient).pref("userDao123", "track123", 5);
        verify(recommendationClient).pref("userDao123", "track456", 5);
    }

}
