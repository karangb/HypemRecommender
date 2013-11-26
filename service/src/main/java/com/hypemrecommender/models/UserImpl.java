package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.blogapi.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:47
 */
public class UserImpl implements User{
    private final String username;
    private final UserDao userDao;
    private final MusicCloudApi musicCloudApi;

    public UserImpl(final String username, final UserDao userDao, final MusicCloudApi musicCloudApi) {

        this.username = username;
        this.userDao = userDao;
        this.musicCloudApi = musicCloudApi;
    }

    @Override
    public long getId() {
        return userDao.getUserId(this.username);
    }

    @Override
    public void addFavourites(final Collection<CloudTrack> myFavourites) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
