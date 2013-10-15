package com.hypemrecommender.dal;

import com.hypemrecommender.representations.Track;
import com.mongodb.*;
import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 26/09/2013
 * Time: 21:53
 */
public class MongoTrackDao implements TrackDao {
    private final DBCollection modelMap;
    private final DBCollection tracks;

    public MongoTrackDao(DBCollection modelMap, DBCollection tracks) {
        this.modelMap = modelMap;
        this.tracks = tracks;
    }

    @Override
    public Track getTrack(final long modelMapId)
    {
        DBObject modelMapping = modelMap.findOne(new BasicDBObject("long_value", modelMapId));
        ObjectId trackId = (ObjectId)modelMapping.get("element_id");
        DBObject trackDoc = tracks.findOne(new BasicDBObject("_id", trackId));
        return new Track((String)trackDoc.get("title"), (String)trackDoc.get("artist"));
    }
}
