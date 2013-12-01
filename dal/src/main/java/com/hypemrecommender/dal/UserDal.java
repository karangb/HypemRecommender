package com.hypemrecommender.dal;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 01/12/2013
 * Time: 12:17
 */
public interface UserDal {
    long getUserId(String username);
    boolean exists(String username);
}
