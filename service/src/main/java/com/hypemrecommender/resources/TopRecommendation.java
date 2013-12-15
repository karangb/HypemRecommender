package com.hypemrecommender.resources;

import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.models.User;
import com.hypemrecommender.models.UserRepository;
import com.hypemrecommender.representations.TrackRepresentation;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 13/12/2013
 * Time: 16:17
 */
@Path("/topRecommendation")
@Produces(MediaType.APPLICATION_JSON)
public class TopRecommendation {
    private final UserRepository userRepository;

    public TopRecommendation(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Timed
    public TrackRepresentation getTopRecommendation(@QueryParam("userId") String userId,
                                    @QueryParam("prevTrackId") String prevTrackId,
                                    @QueryParam("rating") int rating) {
        User user = userRepository.getUser(userId);
        return user.getTopRecommendation(new CloudId(prevTrackId), rating);
    }

}
