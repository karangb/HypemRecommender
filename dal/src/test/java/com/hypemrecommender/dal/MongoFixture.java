package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/10/2013
 * Time: 21:16
 */
public class MongoFixture {

    public static final String HOST = "127.0.0.1";
    public static final String TEST_DB = "test";
    protected DB testDb;
    protected DBCollection dataModelMap;
    protected DBCollection userCollection;
    protected DBCollection trackCollection;

    @Before
    public void baseSetUp() throws IOException {
        MongoClient client = new MongoClient(HOST);
        testDb = client.getDB(TEST_DB);
        dataModelMap = testDb.createCollection("mongo_data_model_map", new BasicDBObject());
        userCollection = testDb.createCollection("users", new BasicDBObject());
        trackCollection = testDb.createCollection("tracks", new BasicDBObject());
    }

    @After
    public void cleanUp()
    {
        testDb.dropDatabase();
    }
}
