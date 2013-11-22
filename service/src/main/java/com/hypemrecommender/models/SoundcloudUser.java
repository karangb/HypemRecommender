package com.hypemrecommender.models;

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
    public void updateFavourites() {
        //To change body of implemented methods use File | Settings | File Templates.
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
}
