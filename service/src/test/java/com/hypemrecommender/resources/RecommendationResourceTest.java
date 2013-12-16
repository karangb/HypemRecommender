package com.hypemrecommender.resources;

import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.engine.RecommendationEngine;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import com.hypemrecommender.representations.Recommendation;
import com.hypemrecommender.representations.TrackRepresentation;
import com.yammer.dropwizard.testing.ResourceTest;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:19
 */
public class RecommendationResourceTest extends ResourceTest {
    private final TrackRepresentation track1 = new TrackRepresentation("1x990", "track1", "artist1");
    private final TrackRepresentation track2 = new TrackRepresentation("1x991", "track2", "artist2");
    private final RecommendationEngine recommendationEngine = mock(RecommendationEngine.class);
    
    private List<TrackRepresentation> tracks;
    private Recommendation recommendation;
    private UserRepository userRepository;
    private User user;


    @Before
    public void setUp() throws TasteException {
        tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        recommendation = new Recommendation(tracks);
        when(recommendationEngine.getRecommendedTracks("karan")).thenReturn(tracks);
    }

    @Override
    protected void setUpResources() throws Exception {
        userRepository = mock(UserRepository.class);
        user = mock(User.class);
        addResource(new RecommendationResource(userRepository, recommendationEngine));
    }

    @Test
    public void testRatingsGetsTracks() throws Exception{
        assertThat(client().resource("/recommendations").queryParam("username", "karan").get(Recommendation.class)).isEqualTo(recommendation);
        verify(recommendationEngine).getRecommendedTracks("karan");
    }

    @Test
    public void testTopRecommendationWithPrevTrack()
    {
        final TrackRepresentation topRecommendation = new TrackRepresentation();
        when(userRepository.getUser(new CloudId(20999414))).thenReturn(user);
        when(user.getTopRecommendation(new CloudId(122132), 5)).thenReturn(topRecommendation);

        TrackRepresentation result = client().resource("/recommendations/next").
                queryParam("userId", "20999414").
                queryParam("prevTrackId", "122132").
                queryParam("rating", "5").
                get(TrackRepresentation.class);

        Assert.assertThat(result, equalTo(topRecommendation));
    }

    @Test
    public void testTopRecommendation()
    {
        final TrackRepresentation topRecommendation = new TrackRepresentation();
        when(userRepository.getUser(new CloudId(20999414))).thenReturn(user);
        when(user.getTopRecommendation()).thenReturn(topRecommendation);

        TrackRepresentation result = client().resource("/recommendations/top").
                queryParam("userId", "20999414").
                get(TrackRepresentation.class);

        Assert.assertThat(result, equalTo(topRecommendation));
    }
}
