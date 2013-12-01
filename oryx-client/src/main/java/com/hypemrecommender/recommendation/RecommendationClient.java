package com.hypemrecommender.recommendation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 01/12/2013
 * Time: 16:36
 */
public interface RecommendationClient {
    void pref(String userId, String itemId, int rating);
}
