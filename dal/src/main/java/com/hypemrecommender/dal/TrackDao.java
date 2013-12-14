package com.hypemrecommender.dal;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 13/12/2013
 * Time: 14:08
 */
public interface TrackDao {
    String getTitle();

    String getArtist();

    String getStreamUrl();

    String getId();

    String getCloudId();
}
