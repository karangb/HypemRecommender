package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:07
 */
public class SoundcloudRepository implements UserDaoRepository{
    private final DBCollection userCollection;
    private final DBCollection trackCollection;

    public SoundcloudRepository(final DBCollection userCollection, final DBCollection trackCollection) {

        this.userCollection = userCollection;
        this.trackCollection = trackCollection;
    }

    @Override
    public UserDao create(final String userId) {
        return new SoundcloudUserDao(userCollection, trackCollection, Integer.valueOf(userId));
    }

    @Override
    public boolean userExists(final String userId) {
        return userCollection.find(new BasicDBObject("soundcloudId", userId)).limit(1).count() != 0;
    }
}
