package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 28/11/2013
 * Time: 22:08
 */
public class SoundcloudUserDao implements UserDao{
    private final DBCollection userCollection;
    private final DBCollection trackCollection;
    private final int userId;

    public SoundcloudUserDao(final DBCollection userCollection, final DBCollection trackCollection, final int userId) {
        this.userCollection = userCollection;
        this.trackCollection = trackCollection;
        this.userId = userId;
    }

    @Override
    public String getId() {
        return getUserDoc().get("_id").toString();
    }

    @Override
    public String provisionFavourite(final CloudTrack track) {
        if(!trackExists(track.getId()))
        {
            DBObject doc = new BasicDBObject("soundcloudId", track.getId()).
                           append("title", track.getTitle()).
                           append("artist", track.getArtist()).
                           append("streamUrl", track.getStreamUrl());

            trackCollection.insert(doc);
        }
        DBObject doc = trackCollection.findOne(new BasicDBObject("soundcloudId", track.getId()));
        DBObject userDoc = getUserDoc();
        userCollection.update(userDoc, new BasicDBObject("$push", new BasicDBObject("favourites", doc.get("_id"))));
        return doc.get("_id").toString();
    }

    private DBObject getUserDoc() {
        return userCollection.findOne(new BasicDBObject("soundcloudId", userId));
    }

    private boolean trackExists(final String soundcloudId) {
        return trackCollection.find(new BasicDBObject("soundcloudId", soundcloudId)).limit(1).count() != 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SoundcloudUserDao that = (SoundcloudUserDao) o;

        if (userId != that.userId) return false;
        if (trackCollection != null ? !trackCollection.equals(that.trackCollection) : that.trackCollection != null)
            return false;
        if (userCollection != null ? !userCollection.equals(that.userCollection) : that.userCollection != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userCollection != null ? userCollection.hashCode() : 0;
        result = 31 * result + (trackCollection != null ? trackCollection.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}

