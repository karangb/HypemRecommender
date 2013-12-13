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
    private RecommendationClient recommendationClient;

    public UserImplRepository(final UserDaoRepository userDaoRepository, RecommendationClient recommendationClient) {
        this.userDaoRepository = userDaoRepository;
        this.recommendationClient = recommendationClient;
    }

    @Override
    public User createUser(final String primaryId) {
        return new UserImpl(userDaoRepository.createUserDao(primaryId), recommendationClient);
    }

    @Override
    public boolean exists(final String userId) {
        return this.userDaoRepository.userExists(userId);
    }

    @Override
    public User getUser(final String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
