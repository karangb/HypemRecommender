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
    private final MusicCloudApi musicCloudApi;

    public UserRepositoryImpl(final UserDao userDao, final MusicCloudApi musicCloudApi) {
        this.userDao = userDao;
        this.musicCloudApi = musicCloudApi;
    }

    @Override
    public User createUser(final String primaryId) {
        return new UserImpl(primaryId, userDao, musicCloudApi);
    }

    @Override
    public boolean exists(final String userId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
