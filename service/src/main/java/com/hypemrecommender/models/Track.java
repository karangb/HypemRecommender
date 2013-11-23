package com.hypemrecommender.models;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 19/11/2013
 * Time: 18:53
 */
public interface Track {
    Collection<User> getFavouritedBy();

    boolean exists();

    boolean provision();
}
