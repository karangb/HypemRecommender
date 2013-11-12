package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.hypemrecommender.representations.ObsessedRepresentation;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 21:33
 */
public class FakeRepresentations {
    public static HypemUserRepresentation userRepresentation()
    {
        ObsessedRepresentation obsessedRepresentation = obsessed();
        return new HypemUserRepresentation("karan", obsessedRepresentation);
    }

    public  static ObsessedRepresentation obsessed() {
        List<HypemTrackRepresentation> tracks = new ArrayList<>();

        tracks.add(track1());
        tracks.add(track2());
        return new ObsessedRepresentation(tracks);
    }

    public static HypemTrackRepresentation track1()
    {
        return new HypemTrackRepresentation("1a13f", "Parov Stelar", "Parov Stelar", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");
    }

    public static HypemTrackRepresentation track2()
    {
        return new HypemTrackRepresentation("qfxh", "Caravan Palace", "Jolie Coquine", 21, "http://static-ak.hypem.net/thumbs_new/4c/2267468.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_120.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_320.jpg");
    }

    public static DBObject userRepresentationDoc() {
        HypemUserRepresentation userRepresentation = userRepresentation();
        return BasicDBObjectBuilder.start().add("username", userRepresentation.getName())
                                           .add("obsessed", obsessedDoc())
                                           .get();
    }

    public static DBObject obsessedDoc() {
        List<DBObject> tracks = new ArrayList<>();
        tracks.add(track1Doc());
        tracks.add(track2Doc());
        return BasicDBObjectBuilder.start().add("tracks", tracks).get();
    }

    public static DBObject track1Doc() {
        return BasicDBObjectBuilder.start().add("media_id", "1a13f")
                                           .add("artist", "The Golden Boy")
                                           .add("artist", "Parov Stelar")
                                           .add("plays", 30)
                                           .add("thumb_url", "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg")
                                           .add("thumb_url_medium", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg")
                                           .add("thumb_url_large", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg")
                                           .get();

    }

    public static DBObject track2Doc() {
        return BasicDBObjectBuilder.start().add("media_id", "qfxh")
                                           .add("artist", "Caravan Palace")
                                           .add("artist", "Jolie Coquine")
                                           .add("plays", 21)
                                           .add("thumb_url", "http://static-ak.hypem.net/thumbs_new/4c/2267468.jpg")
                                           .add("thumb_url_medium", "http://static-ak.hypem.net/thumbs_new/4c/2267468_120.jpg")
                                           .add("thumb_url_large", "http://static-ak.hypem.net/thumbs_new/4c/2267468_320.jpg")
                                           .get();
    }

}
