package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.ObsessedRepresentation;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 12/11/2013
 * Time: 21:07
 */
public class BasicDBObjectFactory {
    public DBObject createTrack(final HypemTrackRepresentation trackRepresentation) {
        return BasicDBObjectBuilder.start().add("media_id", trackRepresentation.getMediaId())
                .add("artist", trackRepresentation.getArtist())
                .add("plays", trackRepresentation.getPlays())
                .add("thumb_url", trackRepresentation.getThumbUrl())
                .add("thumb_url_medium", trackRepresentation.getThumbUrlMedium())
                .add("thumb_url_large", trackRepresentation.getThumbUrlLarge())
                .get();
    }


    public DBObject createObsessed(final ObsessedRepresentation obsessed) {
        List<DBObject> tracks = new ArrayList<>();
        for(HypemTrackRepresentation track : obsessed.getTracks())
        {
            tracks.add(createTrack(track));
        }
        return new BasicDBObject("tracks", tracks);
    }
}
