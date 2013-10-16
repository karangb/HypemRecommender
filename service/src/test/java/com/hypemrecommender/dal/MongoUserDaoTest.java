package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/10/2013
 * Time: 21:22
 */
public class MongoUserDaoTest extends MongoFixture{
    private BasicDBObject userDoc;
    private MongoUserDao userDao;

    @Before
    public void setUp() throws UnknownHostException {
        DBCollection users = testDb.createCollection("users", new BasicDBObject());

        userDoc = new BasicDBObject("username", "karan");
        users.insert(userDoc);

        userDao = new MongoUserDao(dataModelMap, users);
    }

    @Test
    public void testGetUser()
    {
        ObjectId objectId = (ObjectId)userDoc.get("_id");
        dataModelMap.insert(new BasicDBObject("element_id", objectId.toString()).append("long_value", "45"));

        assertEquals(45, userDao.getUserId("karan"));
    }
}
