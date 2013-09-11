package com.hypemrecommender.engine;

import com.hypemrecommender.dal.TrackDao;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.representations.Recommendation;
import com.hypemrecommender.representations.Track;
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
    private final UserDao userDao;
    private final TrackDao trackDao;

    public HypemRecommender(final Recommender mahout, final UserDao userDao, final TrackDao trackDao) {
        this.mahout = mahout;
        this.userDao = userDao;
        this.trackDao = trackDao;
    }

    @Override
    public Recommendation getRating(String username) {
        return null;
    }

    @Override
    public List<Track> getRecommendedTracks(final String username) throws TasteException {
        int id = userDao.getUserId(username);
        List<RecommendedItem> recommendedItems = mahout.recommend(id, 100);
        List<Track> recommendedTracks = new ArrayList<>();
        for(RecommendedItem recommendedItem : recommendedItems)
        {
            recommendedTracks.add(trackDao.getTrack(recommendedItem.getItemID()));
        }
        return recommendedTracks;
    }
}
