package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.dal.TrackDao;
import com.hypemrecommender.dal.TrackDaoRepository;
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
    @Mock TrackDaoRepository trackDaoRepository;
    @Mock TrackDao topTrackDao;
    @Mock TrackDao ratedTrackDao;
    private ArrayList<CloudTrack> favourites;
    private UserImpl user;
    private TrackRepresentation topTrack;

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

        when(ratedTrackDao.getId()).thenReturn("track123");
        when(topTrackDao.getId()).thenReturn("track456");
        when(topTrackDao.getCloudId()).thenReturn("456");
        when(topTrackDao.getTitle()).thenReturn("myTitle");
        when(topTrackDao.getArtist()).thenReturn("myArtist");
        when(topTrackDao.getStreamUrl()).thenReturn("myStreamUrl");

        topTrack = new TrackRepresentation("456", "myTitle", "myArtist", "myStreamUrl");
        user = new UserImpl(userDao, trackDaoRepository, recommendationClient);
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
    public void testGetTopRecommendationWithPrevTrack()
    {
        CloudId ratedCloudTrackId = new CloudId(123);
        when(trackDaoRepository.get(ratedCloudTrackId)).thenReturn(ratedTrackDao);

        when(recommendationClient.getTopRecommendation("userDao123")).thenReturn("track456");
        when(trackDaoRepository.get("track456")).thenReturn(topTrackDao);

        TrackRepresentation topRecommendation = user.getTopRecommendation(ratedCloudTrackId, 3);

        assertThat(topRecommendation, equalTo(topTrack));
        verify(recommendationClient).pref("userDao123", "track123", 3);
    }

    @Test
    public void testGetTopRecommendation()
    {

        when(recommendationClient.getTopRecommendation("userDao123")).thenReturn("track456");
        when(trackDaoRepository.get("track456")).thenReturn(topTrackDao);

        TrackRepresentation topRecommendation = user.getTopRecommendation();

        assertThat(topRecommendation, equalTo(topTrack));
    }
}
