package com.hypemrecommender.engine;

import com.hypemrecommender.dal.TrackDal;
import com.hypemrecommender.dal.UserDal;
import com.hypemrecommender.representations.TrackRepresentation;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 06/09/2013
 * Time: 22:06
 */
public class HypemRecommender implements RecommendationEngine {
    private final Recommender mahout;
    private final UserDal userDal;
    private final TrackDal trackDal;

    public HypemRecommender(final Recommender mahout, final UserDal userDal, final TrackDal trackDal) {
        this.mahout = mahout;
        this.userDal = userDal;
        this.trackDal = trackDal;
    }

    @Override
    public List<TrackRepresentation> getRecommendedTracks(final String username) throws TasteException {
        long id = userDal.getUserId(username);
        List<RecommendedItem> recommendedItems = mahout.recommend(id, 100);
        List<TrackRepresentation> recommendedTracks = new ArrayList<>();
        for(RecommendedItem recommendedItem : recommendedItems)
        {
            recommendedTracks.add(trackDal.getTrack(recommendedItem.getItemID()));
        }
        return recommendedTracks;
    }
}
