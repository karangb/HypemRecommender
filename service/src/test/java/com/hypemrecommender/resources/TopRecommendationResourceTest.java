package com.hypemrecommender.resources;

import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import com.hypemrecommender.representations.TrackRepresentation;
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 02/12/2013
 * Time: 19:36
 */
public class TopRecommendationResourceTest extends ResourceTest {

    private UserRepository userRepository;
    private User user;

    @Override
    protected void setUpResources() throws Exception {
        userRepository = mock(UserRepository.class);
        user = mock(User.class);
        addResource(new TopRecommendation(userRepository));
    }

    @Test
    public void testTopRecommendation()
    {
        final TrackRepresentation topRecommendation = new TrackRepresentation();
        when(userRepository.getUser("abc20999414")).thenReturn(user);
        when(user.getTopRecommendation("def122132", 5)).thenReturn(topRecommendation);

        TrackRepresentation result = client().resource("/topRecommendation").
                queryParam("userId", "abc20999414").
                queryParam("prevTrackId", "def122132").
                queryParam("rating", "5").
                get(TrackRepresentation.class);

        Assert.assertThat(result, equalTo(topRecommendation));
    }
}
