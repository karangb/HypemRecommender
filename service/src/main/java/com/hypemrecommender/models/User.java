package com.hypemrecommender.models;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.dal.CloudId;
import com.hypemrecommender.representations.TrackRepresentation;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:39
 */
public interface User {
    void addFavourites(Collection<CloudTrack> favourites);

    void setTrackPref(String trackId, int pref);

    TrackRepresentation getTopRecommendation(CloudId trackId, int pref);

    TrackRepresentation getTopRecommendation();
}
