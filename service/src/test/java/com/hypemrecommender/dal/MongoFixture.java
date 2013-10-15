package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
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
    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    protected DB testDb;
    protected DBCollection dataModelMap;

    @Before
    public void baseSetUp() throws IOException {
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.DEVELOPMENT).build();
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        mongodExe = runtime.prepare(mongodConfig);
        mongod = mongodExe.start();
        MongoClient client = new MongoClient(HOST);
        testDb = client.getDB("test");
        dataModelMap = testDb.createCollection("mongo_data_model_map", new BasicDBObject());
    }

    @After
    public void cleanUp()
    {
        testDb.dropDatabase();
        mongod.stop();
        mongodExe.stop();
    }
}
