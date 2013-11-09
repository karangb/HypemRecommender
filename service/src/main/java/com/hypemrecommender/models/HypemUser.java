package com.hypemrecommender.models;

import com.hypemrecommender.Crawler;
import com.hypemrecommender.dal.UserDao;
import com.hypemrecommender.representations.HypemUserRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:47
 */
public class HypemUser implements User{
    private final String username;
    private final UserDao userDao;
    private final Crawler crawler;

    public HypemUser(final String username, final UserDao userDao, final Crawler crawler) {

        this.username = username;
        this.userDao = userDao;
        this.crawler = crawler;
    }

    @Override
    public long getId() {
        return userDao.getUserId(this.username);
    }

    @Override
    public void updateFavourites() {
        if(!userDao.exists(username))
        {
            HypemUserRepresentation userRepresentation = crawler.fetchUser(username);
            userDao.provision(userRepresentation);
        }
    }
}
