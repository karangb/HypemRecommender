package com.hypemrecommender.models;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 20/11/2013
 * Time: 21:33
 */
public class SoundcloudUserFactory implements UserFactory {
    @Override
    public User createUser(final String soundcloudId) {
        return new SoundcloudUser(soundcloudId);
    }
}
