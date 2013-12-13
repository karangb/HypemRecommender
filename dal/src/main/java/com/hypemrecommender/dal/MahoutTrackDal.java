package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.TrackRepresentation;
import com.mongodb.*;
import org.bson.types.ObjectId;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 26/09/2013
 * Time: 21:53
 */
public class MahoutTrackDal implements TrackDal {
    private final DBCollection modelMap;
    private final DBCollection tracks;

    public MahoutTrackDal(DBCollection modelMap, DBCollection tracks) {
        this.modelMap = modelMap;
        this.tracks = tracks;
    }

    @Override
    public TrackRepresentation getTrack(final long modelMapId)
    {
        DBObject modelMapping = modelMap.findOne(new BasicDBObject("long_value", String.valueOf(modelMapId)));
        String trackId = (String)modelMapping.get("element_id");
        ObjectId bsonTrackId = new ObjectId(trackId);
        DBObject trackDoc = tracks.findOne(new BasicDBObject("_id", bsonTrackId));
        return new TrackRepresentation((String)trackDoc.get("media_id"), (String)trackDoc.get("title"), (String)trackDoc.get("artist"));
    }

    @Override
    public boolean exists(final HypemTrackRepresentation trackRepresentation) {
        return false;
    }
}
