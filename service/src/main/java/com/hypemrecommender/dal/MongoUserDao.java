package com.hypemrecommender.dal;

import com.mongodb.*;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/10/2013
 * Time: 21:15
 */
public class MongoUserDao implements UserDao{
    private final DBCollection modelMap;
    private final DBCollection users;

    public MongoUserDao(DBCollection modelMap, DBCollection users) {
        this.modelMap = modelMap;
        this.users = users;
    }

    @Override
    public int getUserId(final String username) {
        final DBObject user = users.findOne(new BasicDBObject("username", username));
        DBObject mappedUser = modelMap.findOne(new BasicDBObject("element_id", user.get("_id")));
        return (int)mappedUser.get("long_value");
    }
}
