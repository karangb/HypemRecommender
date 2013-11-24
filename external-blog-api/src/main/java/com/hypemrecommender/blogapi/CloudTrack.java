package com.hypemrecommender.blogapi;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 24/11/2013
 * Time: 01:23
 */
public interface CloudTrack {
    Collection<String> getFavouritedBy();

    String getId();

    String getArtist();

    String getTitle();

    String getStreamUrl();
}
