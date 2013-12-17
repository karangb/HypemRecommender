package com.hypemrecommender.resources;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.engine.RecommendationEngine;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import com.hypemrecommender.representations.Recommendation;
import com.hypemrecommender.representations.TrackRepresentation;
import com.yammer.metrics.annotation.Timed;
import org.apache.mahout.cf.taste.common.TasteException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/09/2013
 * Time: 22:35
 */
@Path("/recommendations")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {
    private final UserRepository userRepository;
    private final RecommendationEngine recommendationEngine;
    private final MusicCloudApi musicCloudApi;

    public RecommendationResource(final UserRepository userRepository, RecommendationEngine recommendationEngine, final MusicCloudApi musicCloudApi) {
        this.userRepository = userRepository;
        this.recommendationEngine = recommendationEngine;
        this.musicCloudApi = musicCloudApi;
    }

    @GET
    @Timed
    public Recommendation getRating(@QueryParam("username") String username) throws TasteException {
        return new Recommendation(recommendationEngine.getRecommendedTracks(username));
    }

    @GET
    @Timed
    @Path("/top")
    public TrackRepresentation getTopRecommendation(@QueryParam("userId") String userId) throws IOException {
        CloudId cloudUserId = new CloudId(Integer.valueOf(userId));
        User user;
        if(!userRepository.exists(cloudUserId))
        {
            Collection<CloudTrack> favs = musicCloudApi.fetchFavourites(cloudUserId.toString());
            user = userRepository.createUser(cloudUserId);
            user.addFavourites(favs);
        }
        else
        {
            user = userRepository.getUser(cloudUserId);
        }

        return user.getTopRecommendation();
    }

    @GET
    @Timed
    @Path("/next")
    public TrackRepresentation getTopRecommendation(@QueryParam("userId") String userId,
                                                    @QueryParam("prevTrackId") String prevTrackId,
                                                    @QueryParam("rating") int rating) {
        User user = userRepository.getUser(new CloudId(Integer.valueOf(userId)));
        return user.getTopRecommendation(new CloudId(Integer.valueOf(prevTrackId)), rating);
    }
}
