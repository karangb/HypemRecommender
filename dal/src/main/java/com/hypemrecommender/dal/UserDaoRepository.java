package com.hypemrecommender.dal;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 26/11/2013
 * Time: 23:22
 */
public interface UserDaoRepository {
    UserDao create(String username);
    boolean userExists(String userId);
}
