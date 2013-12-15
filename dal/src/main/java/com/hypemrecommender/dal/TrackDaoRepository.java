package com.hypemrecommender.dal;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 14/12/2013
 * Time: 13:58
 */
public interface TrackDaoRepository {
    TrackDao get(String cloudTrackId);

    TrackDao get(CloudId cloudTrackId);
}
