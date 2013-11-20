package com.hypemrecommender.models;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:39
 */
public interface User {
    long getId();

    void updateFavourites();

    Void provision();

    Collection<Track> getFavourites();

    boolean exists();
}
