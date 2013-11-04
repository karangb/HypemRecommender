package com.hypemrecommender;

import com.hypemrecommender.representations.UserRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:58
 */
public interface Crawler {
    UserRepresentation fetchUser(String username);
}
