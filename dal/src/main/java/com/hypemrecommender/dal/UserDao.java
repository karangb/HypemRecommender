package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 11/09/2013
 * Time: 22:01
 */
public interface UserDao {
    long getUserId(String username);
    boolean exists(String username);
    void provisionFavourite(CloudTrack track);
}
