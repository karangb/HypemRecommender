package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.hypemrecommender.representations.ObsessedRepresentation;

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
        List<HypemTrackRepresentation> tracks = new ArrayList<>();

        tracks.add(track1());
        tracks.add(track2());
        ObsessedRepresentation obsessedRepresentation = new ObsessedRepresentation(tracks);
        return new HypemUserRepresentation("karan", obsessedRepresentation);
    }

    public static HypemTrackRepresentation track1()
    {
        return new HypemTrackRepresentation("1a13f", "Parov Stelar", "Parov Stelar", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");
    }

    public static HypemTrackRepresentation track2()
    {
        return new HypemTrackRepresentation("qfxh", "Caravan Palace", "Jolie Coquine", 21, "http://static-ak.hypem.net/thumbs_new/4c/2267468.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_120.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_320.jpg");
    }

}
