package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static com.mongodb.util.MyAsserts.assertFalse;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/10/2013
 * Time: 21:22
 */
public class MahoutUserDaoTest extends MongoFixture{
    private BasicDBObject userDoc;
    private MahoutUserDao userDao;

    @Before
    public void setUp() throws UnknownHostException {
        DBCollection users = testDb.createCollection("users", new BasicDBObject());

        userDoc = new BasicDBObject("username", "karan");
        users.insert(userDoc);

        userDao = new MahoutUserDao(dataModelMap, users);
    }

    @Test
    public void testGetUser()
    {
        ObjectId objectId = (ObjectId)userDoc.get("_id");
        dataModelMap.insert(new BasicDBObject("element_id", objectId.toString()).append("long_value", "45"));

        assertEquals(45, userDao.getUserId("karan"));
    }

    @Test
    public void testExists()
    {
        assertTrue(userDao.exists("karan"));
        assertFalse(userDao.exists("john"));
    }
}
