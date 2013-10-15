package com.hypemrecommender.dal;

import com.hypemrecommender.representations.Track;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 26/09/2013
 * Time: 21:53
 */
public class MongoTrackDao implements com.hypemrecommender.dal.TrackDao {


    private final DBCollection modelMap;
    private final DBCollection tracks;

    public MongoTrackDao(String host, String dbName) throws UnknownHostException {
        MongoClient client = new MongoClient(host);
        DB db = client.getDB(dbName);
        modelMap = db.getCollection("mongo_data_model_map");
        tracks = db.getCollection("tracks");
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
