package com.hypemrecommender.engine;

import com.hypemrecommender.representations.TrackRepresentation;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:25
 */
public interface RecommendationEngine {
    List<TrackRepresentation> getRecommendedTracks(String username) throws TasteException;
}
