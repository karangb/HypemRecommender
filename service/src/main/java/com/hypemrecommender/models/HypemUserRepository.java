package com.hypemrecommender.models;

import com.hypemrecommender.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class HypemUserRepository implements UserRepository {

    private final UserDao userDao;
    private final MusicCloudApi musicCloudApi;

    public HypemUserRepository(final UserDao userDao, final MusicCloudApi musicCloudApi) {
        this.userDao = userDao;
        this.musicCloudApi = musicCloudApi;
    }

    @Override
    public User createUser(final String primaryId) {
        return new HypemUser(primaryId, userDao, musicCloudApi);
    }
}
