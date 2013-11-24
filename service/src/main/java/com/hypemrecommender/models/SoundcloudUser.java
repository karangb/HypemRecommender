package com.hypemrecommender.models;

import com.hypemrecommender.CloudTrack;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 22/11/2013
 * Time: 23:08
 */
public class SoundcloudUser implements User{
    public SoundcloudUser(final String soundcloudId) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public long getId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addFavourites(final Collection<CloudTrack> myFavourites) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
