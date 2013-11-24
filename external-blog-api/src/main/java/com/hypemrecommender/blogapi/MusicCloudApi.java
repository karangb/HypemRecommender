package com.hypemrecommender.blogapi;

import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:58
 */
public interface MusicCloudApi {
    Collection<CloudTrack> fetchFavourites(String unCrawledUser) throws IOException;
}
