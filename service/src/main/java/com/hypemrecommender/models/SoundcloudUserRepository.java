package com.hypemrecommender.models;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 20/11/2013
 * Time: 21:33
 */
public class SoundcloudUserRepository implements UserRepository {
    @Override
    public User createUser(final String soundcloudId) {
        return new SoundcloudUser(soundcloudId);
    }

    @Override
    public boolean exists(final String userId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
