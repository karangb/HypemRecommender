package com.hypemrecommender.models;

import com.hypemrecommender.CloudTrack;
import com.hypemrecommender.MusicCloudApi;
import com.hypemrecommender.dal.UserDao;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:47
 */
public class HypemUser implements User{
    private final String username;
    private final UserDao userDao;

    public HypemUser(final String username, final UserDao userDao, final MusicCloudApi musicCloudApi) {

        this.username = username;
        this.userDao = userDao;
        this.musicCloudApi = musicCloudApi;
    }

    @Override
    public long getId() {
        return userDao.getUserId(this.username);
    }

    @Override
    public Void provision() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Track> getFavourites() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean exists() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addFavourites(final Collection<CloudTrack> myFavourites) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
