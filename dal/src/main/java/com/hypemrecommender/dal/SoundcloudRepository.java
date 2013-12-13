package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import java.util.LinkedList;

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
    public UserDao createUserDao(final String userId) {
        int soundcloudUserId = Integer.valueOf(userId);
        BasicDBObject doc = new BasicDBObject("soundcloudId", soundcloudUserId).append("favourites", new LinkedList<String>());
        userCollection.insert(doc);
        return new SoundcloudUserDao(userCollection, trackCollection, soundcloudUserId);
    }

    @Override
    public boolean userExists(final String userId) {
        return userCollection.find(new BasicDBObject("soundcloudId", Integer.valueOf(userId))).limit(1).count() != 0;
    }

    public TrackDao createTrackDao(final String trackId,
                                   final String title,
                                   final String artist,
                                   final String streamUrl) {

        int soundcloudTrackId = Integer.valueOf(trackId);
        BasicDBObject doc = new BasicDBObject("soundcloudId", soundcloudTrackId).
                            append("title", title).
                            append("artist", artist).
                            append("streamUrl", streamUrl);
        trackCollection.insert(doc);
        return new SoundcloudTrackDao(trackCollection, trackId);
    }

    public boolean trackExists(final String trackId) {
        return trackCollection.find(new BasicDBObject("soundcloudId", Integer.valueOf(trackId))).limit(1).count() != 0;
    }
}
