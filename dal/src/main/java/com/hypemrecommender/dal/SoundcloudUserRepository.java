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
public class SoundcloudUserRepository implements UserDaoRepository{
    private final DBCollection userCollection;
    private final DBCollection trackCollection;

    public SoundcloudUserRepository(final DBCollection userCollection, final DBCollection trackCollection) {

        this.userCollection = userCollection;
        this.trackCollection = trackCollection;
    }

    @Override
    public UserDao createUserDao(final CloudId cloudUserId) {
        int soundcloudUserId = cloudUserId.getId();
        BasicDBObject doc = new SoundcloudDBObject(soundcloudUserId).append("favourites", new LinkedList<String>());
        userCollection.insert(doc);
        return new SoundcloudUserDao(userCollection, trackCollection, soundcloudUserId);
    }

    @Override
    public boolean userExists(final CloudId soundcloudId) {
        return soundcloudResourceExists(userCollection, soundcloudId);
    }

    @Override
    public UserDao get(final CloudId cloudUserId) {
        return new SoundcloudUserDao(userCollection, trackCollection, cloudUserId.getId());
    }

    public TrackDao createTrackDao(final CloudId cloudId,
                                   final String title,
                                   final String artist,
                                   final String streamUrl) {

        BasicDBObject doc = new SoundcloudDBObject(cloudId.getId()).
                            append("title", title).
                            append("artist", artist).
                            append("streamUrl", streamUrl);
        trackCollection.insert(doc);
        return new SoundcloudTrackDao(trackCollection, cloudId);
    }

    public boolean trackExists(final CloudId trackId) {
        return soundcloudResourceExists(trackCollection, trackId);
    }

    private boolean soundcloudResourceExists(DBCollection collection, CloudId soundcloudId)
    {
        return collection.find(new SoundcloudDBObject(soundcloudId.getId())).limit(1).count() != 0;
    }

}
