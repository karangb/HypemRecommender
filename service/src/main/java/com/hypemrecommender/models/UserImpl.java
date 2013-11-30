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
    private final UserDao userDao;

    public UserImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addFavourites(final Collection<CloudTrack> favourites) {
        for(CloudTrack track : favourites)
        {
            userDao.provisionFavourite(track);
        }
    }
}
