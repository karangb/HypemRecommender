package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.recommendation.RecommendationClient;
import com.hypemrecommender.representations.TrackRepresentation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
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
    @Mock TrackRepository trackRepository;
    private ArrayList<CloudTrack> favourites;
    private UserImpl user;



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
        user = new UserImpl(userDao, trackRepository, recommendationClient);
    }

    @Test
    public void testAddFavourites() throws IOException {
        user.addFavourites(favourites);

        verify(userDao).provisionFavourite(track1);
        verify(userDao).provisionFavourite(track2);
        verify(recommendationClient).pref("userDao123", "track123", 5);
        verify(recommendationClient).pref("userDao123", "track456", 5);
    }

    @Test
    public void testSetTrackPref()
    {
       user.setTrackPref("track123", 3);
       verify(recommendationClient).pref("userDao123", "track123", 3);
    }

    @Test
    public void testGetTopRecommendation()
    {
        final TrackRepresentation topTrack = new TrackRepresentation();
        when(recommendationClient.getTopRecommendation("userDao123")).thenReturn("track456");
        when(trackRepository.get("track456")).thenReturn(topTrack); 
        

        assertThat(user.getTopRecommendation("track123", 3), equalTo(topTrack));
        verify(recommendationClient).pref("userDao123", "track123", 3);
    }
}
