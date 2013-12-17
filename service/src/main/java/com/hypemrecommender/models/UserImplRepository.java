package com.hypemrecommender.models;

import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.dal.TrackDaoRepository;
import com.hypemrecommender.dal.UserDaoRepository;
import com.hypemrecommender.recommendation.RecommendationClient;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class UserImplRepository implements UserRepository {

    private final UserDaoRepository userDaoRepository;
    private final TrackDaoRepository trackRepository;
    private RecommendationClient recommendationClient;
    private final String clientId;

    public UserImplRepository(final UserDaoRepository userDaoRepository, final TrackDaoRepository trackRepository, RecommendationClient recommendationClient, String clientId) {
        this.userDaoRepository = userDaoRepository;
        this.trackRepository = trackRepository;
        this.recommendationClient = recommendationClient;
        this.clientId = clientId;
    }

    @Override
    public User createUser(final CloudId cloudUserId) {
        return new UserImpl(userDaoRepository.createUserDao(cloudUserId), trackRepository, recommendationClient, clientId);
    }

    @Override
    public boolean exists(final CloudId cloudUserId) {
        return this.userDaoRepository.userExists(cloudUserId);
    }

    @Override
    public User getUser(final CloudId cloudUserId) {
        return new UserImpl(this.userDaoRepository.get(cloudUserId), trackRepository, recommendationClient, clientId);
    }
}
