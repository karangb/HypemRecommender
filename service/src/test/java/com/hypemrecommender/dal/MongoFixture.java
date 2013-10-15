package com.hypemrecommender.dal;

import com.mongodb.DB;
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

    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    protected DB testDb;

    @Before
    public void setUp() throws IOException {
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.DEVELOPMENT).build();
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        mongodExe = runtime.prepare(mongodConfig);
        mongod = mongodExe.start();
        MongoClient client = new MongoClient("127.0.0.1");
        testDb = client.getDB("test");
    }

    @After
    public void cleanUp()
    {
        testDb.dropDatabase();
        mongod.stop();
        mongodExe.stop();
    }
}
