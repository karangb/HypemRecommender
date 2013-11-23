package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.TrackRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 11/09/2013
 * Time: 22:13
 */
public interface TrackDao {
    TrackRepresentation getTrack(long itemID);

    boolean exists(HypemTrackRepresentation trackRepresentation);

    void provision(HypemTrackRepresentation trackRepresentation);
}
