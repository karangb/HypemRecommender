package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.mongodb.*;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/10/2013
 * Time: 21:15
 */
public class MahoutUserDao implements UserDao{
    private final DBCollection modelMap;
    private final DBCollection users;

    public MahoutUserDao(DBCollection modelMap, DBCollection users) {
        this.modelMap = modelMap;
        this.users = users;
    }

    @Override
    public long getUserId(final String username) {
        final DBObject user = users.findOne(new BasicDBObject("username", username));
        DBObject mappedUser = modelMap.findOne(new BasicDBObject("element_id", user.get("_id").toString()));
        return Long.valueOf((String)mappedUser.get("long_value"));
    }

    @Override
    public boolean exists(final String username) {
        return users.count(new BasicDBObject("username", username)) > 0;
    }

    @Override
    public void provision(final HypemUserRepresentation userRepresentation) {
        //TODO
    }

    @Override
    public void provisionFavourite(final CloudTrack track) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
