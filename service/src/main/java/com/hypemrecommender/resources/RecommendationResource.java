package com.hypemrecommender.resources;

import com.hypemrecommender.dao.RecommendationEngine;
import com.hypemrecommender.representations.Recommendation;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/09/2013
 * Time: 22:35
 */
@Path("/recommendations")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {
    private RecommendationEngine recommendationEngine;

    public RecommendationResource(RecommendationEngine recommendationEngine) {
        this.recommendationEngine = recommendationEngine;
    }

    @GET
    @Timed
    public Recommendation getRating(@QueryParam("username") String username)  {
        return recommendationEngine.getRating(username);
    }
}
