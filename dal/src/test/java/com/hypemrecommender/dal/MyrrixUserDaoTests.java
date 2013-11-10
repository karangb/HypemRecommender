package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.hypemrecommender.representations.ObsessedRepresentation;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 01:31
 */
public class MyrrixUserDaoTests extends MongoFixture{
    
    private MyrrixUserDao userDao;
    private DBCollection userCollection;

    @Before
    public void setUp() throws UnknownHostException {
        userCollection = testDb.createCollection("hypemUsers", new BasicDBObject());
        userDao = new MyrrixUserDao(userCollection);
    }

    @Test
    public void testProvision()
    {
        HypemUserRepresentation userRepresentation = createFakeUserRepresentation();
        
        userDao.provision(userRepresentation);

        DBObject myUser = userCollection.findOne(new BasicDBObject("username", "karan"));
        ArrayList<DBObject> tracks = (ArrayList<DBObject>) myUser.get("obsession");

        assertThat((String) tracks.get(0).get("media_id"), equalTo("1a13f"));
        assertThat((Integer) tracks.get(0).get("plays"), equalTo(30));

        assertThat((String) tracks.get(1).get("media_id"), equalTo("qfxh"));
        assertThat((Integer) tracks.get(1).get("plays"), equalTo(21));
    }

    private HypemUserRepresentation createFakeUserRepresentation() {
        HypemTrackRepresentation trackRepresentation1 = new HypemTrackRepresentation("1a13f", "Parov Stelar", "Parov Stelar", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");
        HypemTrackRepresentation trackRepresentation2 = new HypemTrackRepresentation("qfxh", "Caravan Palace", "Jolie Coquine", 21, "http://static-ak.hypem.net/thumbs_new/4c/2267468.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_120.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_320.jpg");
        List<HypemTrackRepresentation> tracks = new ArrayList<>();
        tracks.add(trackRepresentation1);
        tracks.add(trackRepresentation2);
        ObsessedRepresentation obsessedRepresentation = new ObsessedRepresentation(tracks);
        return new HypemUserRepresentation("karan", obsessedRepresentation);
    }
}
