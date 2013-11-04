package com.hypemrecommender.models;

import com.hypemrecommender.Crawler;
import com.hypemrecommender.dal.UserDao;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public class HypemUserRepository implements UserRepository{

    private final UserDao userDao;
    private final Crawler crawler;

    public HypemUserRepository(final UserDao userDao, final Crawler crawler) {
        this.userDao = userDao;
        this.crawler = crawler;
    }

    @Override
    public User getUser(final String username) {
        return new HypemUser(username, userDao, crawler);
    }
}
