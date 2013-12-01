package com.hypemrecommender.models;

import com.hypemrecommender.dal.UserDaoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class UserImplRepository implements UserRepository {

    private final UserDaoRepository userDaoRepository;

    public UserImplRepository(final UserDaoRepository userDaoRepository) {

        this.userDaoRepository = userDaoRepository;
    }

    @Override
    public User createUser(final String primaryId) {
        return new UserImpl(userDaoRepository.create(primaryId));
    }

    @Override
    public boolean exists(final String userId) {
        return this.userDaoRepository.userExists(userId);
    }
}
