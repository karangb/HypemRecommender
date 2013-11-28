package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.representations.HypemUserRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:08
 */
public class SoundcloudUserDao implements UserDao{
    private final String userId;

    public SoundcloudUserDao(final String userId) {
        this.userId = userId;
    }

    @Override
    public long getUserId(final String username) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean exists(final String username) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void provision(final HypemUserRepresentation userRepresentation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void provisionFavourite(final CloudTrack track) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
