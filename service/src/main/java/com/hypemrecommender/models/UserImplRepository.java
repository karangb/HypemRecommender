package com.hypemrecommender.models;

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
    private final TrackRepository trackRepository;
    private RecommendationClient recommendationClient;

    public UserImplRepository(final UserDaoRepository userDaoRepository, final TrackRepository trackRepository, RecommendationClient recommendationClient) {
        this.userDaoRepository = userDaoRepository;
        this.trackRepository = trackRepository;
        this.recommendationClient = recommendationClient;
    }

    @Override
    public User createUser(final String cloudUserId) {
        return new UserImpl(userDaoRepository.createUserDao(cloudUserId), trackRepository, recommendationClient);
    }

    @Override
    public boolean exists(final String cloudUserId) {
        return this.userDaoRepository.userExists(cloudUserId);
    }

    @Override
    public User getUser(final String cloudUserId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
