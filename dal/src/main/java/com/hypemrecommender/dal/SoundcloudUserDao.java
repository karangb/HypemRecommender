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
    private final DBObject userDoc;

    public SoundcloudUserDao(final DBCollection userCollection, final DBCollection trackCollection, final int userId) {
        this.userCollection = userCollection;
        this.trackCollection = trackCollection;
        userDoc = userCollection.findOne(new BasicDBObject("soundcloudId", userId));
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
    public void provisionFavourite(final CloudTrack track) {
        if(!trackExists(track.getId()))
        {
            DBObject doc = new BasicDBObject("soundcloudId", track.getId()).
                           append("title", track.getTitle()).
                           append("artist", track.getArtist()).
                           append("streamUrl", track.getStreamUrl());

            trackCollection.insert(doc);
        }
        DBObject doc = trackCollection.findOne(new BasicDBObject("soundcloudId", track.getId()));
        userCollection.update(userDoc, new BasicDBObject("$push", new BasicDBObject("favourites", doc.get("_id"))));
    }

    private boolean trackExists(final String soundcloudId) {
        return trackCollection.find(new BasicDBObject("soundcloudId", soundcloudId)).limit(1).count() != 0;
    }


}

