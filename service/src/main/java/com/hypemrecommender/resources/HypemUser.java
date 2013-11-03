package com.hypemrecommender.resources;

import com.hypemrecommender.dal.UserDao;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:47
 */
public class HypemUser implements User{
    private final String username;
    private final UserDao userDao;

    public HypemUser(final String username, final UserDao userDao) {

        this.username = username;
        this.userDao = userDao;
    }

    @Override
    public long getId() {
        return userDao.getUserId(this.username);
    }
}
