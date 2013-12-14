package com.hypemrecommender.models;

import com.hypemrecommender.representations.TrackRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 14/12/2013
 * Time: 12:52
 */
public interface TrackRepository {
    TrackRepresentation get(String trackId);
}
