package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.dal.TrackDao;
import com.hypemrecommender.dal.TrackDaoRepository;
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
    private final TrackDaoRepository trackDaoRepository;
    private final RecommendationClient recommendationClient;
    private final String clientId;

    public UserImpl(final UserDao userDao, final TrackDaoRepository trackDaoRepository, final RecommendationClient recommendationClient, final String clientId) {
        this.userDao = userDao;
        this.trackDaoRepository = trackDaoRepository;
        this.recommendationClient = recommendationClient;
        this.clientId = clientId;
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
    public TrackRepresentation getTopRecommendation(final CloudId trackId, final int pref) {
        TrackDao recentlyListenedTrack = trackDaoRepository.get(trackId);
        recommendationClient.pref(userDao.getId(), recentlyListenedTrack.getId(), pref);
        return getTopTrack();
    }

    @Override
    public TrackRepresentation getTopRecommendation() {
        return getTopTrack();
    }

    private TrackRepresentation getTopTrack() {
        String recommendedTrackId = recommendationClient.getTopRecommendation(userDao.getId());
        TrackDao recommendedTrack = trackDaoRepository.get(recommendedTrackId);
        return new TrackRepresentation(recommendedTrack.getCloudId(),
                recommendedTrack.getTitle(),
                recommendedTrack.getArtist(),
                recommendedTrack.getStreamUrl() + "?client_id=" + clientId);
    }
}
