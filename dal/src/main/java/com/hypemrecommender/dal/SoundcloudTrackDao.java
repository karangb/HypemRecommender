package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 13/12/2013
 * Time: 14:09
 */
public class SoundcloudTrackDao implements TrackDao {

    private final DBObject doc;

    public SoundcloudTrackDao(final DBCollection trackCollection, final String trackId) {
        doc = trackCollection.findOne(new BasicDBObject("_id", new ObjectId(trackId)));
    }

    public SoundcloudTrackDao(final DBCollection trackCollection, final CloudId cloudTrackId) {
        doc = trackCollection.findOne(new SoundcloudDBObject(cloudTrackId.getId()));
    }

    @Override
    public String getTitle() {
        return doc.get("title").toString();
    }

    @Override
    public String getArtist() {
        return doc.get("artist").toString();
    }

    @Override
    public String getStreamUrl() {
        return doc.get("streamUrl").toString();
    }

    @Override
    public String getId() {
        return doc.get("_id").toString();
    }

    @Override
    public String getCloudId() {
        return doc.get("soundcloudId").toString();
    }
}
