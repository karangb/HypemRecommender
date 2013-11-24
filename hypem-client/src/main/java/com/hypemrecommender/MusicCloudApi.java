package com.hypemrecommender;

import com.hypemrecommender.representations.HypemUserRepresentation;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:58
 */
public interface MusicCloudApi {
    HypemUserRepresentation fetchUser(String username);

    Collection<CloudTrack> fetchFavourites(String unCrawledUser);
}
