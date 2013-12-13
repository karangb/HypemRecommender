package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.recommendation.RecommendationClient;
import com.hypemrecommender.representations.TrackRepresentation;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:47
 */
public class UserImpl implements User{
    private final UserDao userDao;
    private final RecommendationClient recommendationClient;

    public UserImpl(final UserDao userDao, final RecommendationClient recommendationClient) {
        this.userDao = userDao;
        this.recommendationClient = recommendationClient;
    }

    @Override
    public void addFavourites(final Collection<CloudTrack> favourites) {
        for(CloudTrack track : favourites)
        {
            String id = userDao.provisionFavourite(track);
            recommendationClient.pref(userDao.getId(), id, 5);
        }
    }

    @Override
    public void setTrackPref(final String trackId, final int prefValue) {
        recommendationClient.pref(userDao.getId(), trackId, prefValue);
    }

    @Override
    public TrackRepresentation getTopRecommendation(final String s, final int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
