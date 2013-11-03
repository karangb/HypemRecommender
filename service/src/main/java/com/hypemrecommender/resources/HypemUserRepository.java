package com.hypemrecommender.resources;

import com.hypemrecommender.dal.UserDao;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class HypemUserRepository implements UserRepository{

    private final UserDao userDao;

    public HypemUserRepository(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(final String username) {
        return new HypemUser(username, userDao);
    }
}
