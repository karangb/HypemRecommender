package com.hypemrecommender.dao;

import com.hypemrecommender.representations.Recommendation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:25
 */
public interface RecommendationEngine {
    public Recommendation getRating(String username);
}
