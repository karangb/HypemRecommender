package com.hypemrecommender.dal;

import com.hypemrecommender.UserDaoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:07
 */
public class SoundcloudRepository implements UserDaoRepository{
    @Override
    public UserDao create(final String userId) {
        return new SoundcloudUserDao(null, null, Integer.getInteger(userId));
    }
}
