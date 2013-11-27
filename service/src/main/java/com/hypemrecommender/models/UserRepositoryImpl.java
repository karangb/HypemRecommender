package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User createUser(final String primaryId) {
        return new UserImpl(userDao);
    }

    @Override
    public boolean exists(final String userId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
