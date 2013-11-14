package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 01:31
 */
public class MyrrixUserDaoTest extends MongoFixture{
    
    private MyrrixUserDao userDao;
    private DBCollection userCollection;

    private TrackDao trackDao;
    private HypemUserRepresentation userRepresentation;
    private HypemTrackRepresentation trackRepresentation1;
    private HypemTrackRepresentation trackRepresentation2;

    @Before
    public void setUp() throws UnknownHostException {
        userCollection = testDb.createCollection("hypemUsers", new BasicDBObject());

        trackDao = mock(TrackDao.class);
        userRepresentation = FakeRepresentations.userRepresentation();
        trackRepresentation1 = userRepresentation.getObsessed().getTracks().get(0);
        trackRepresentation2 = userRepresentation.getObsessed().getTracks().get(1);
        userDao = new MyrrixUserDao(trackDao, userCollection);
    }

    @Test
    public void testProvision()
    {
        when(trackDao.exists(trackRepresentation1)).thenReturn(true);
        when(trackDao.exists(trackRepresentation2)).thenReturn(true);

        userDao.provision(userRepresentation);

        assertThatUserRepresentationProvisioned();
    }

    @Test
    public void testObsessedTracksAreProvisioned()
    {
        when(trackDao.exists(trackRepresentation1)).thenReturn(true);
        when(trackDao.exists(trackRepresentation2)).thenReturn(false);

        userDao.provision(userRepresentation);

        verify(trackDao, never()).provision(trackRepresentation1);
        verify(trackDao).provision(trackRepresentation2);
    }

    @SuppressWarnings("unchecked")
    private void assertThatUserRepresentationProvisioned() {
        DBObject myUser = userCollection.findOne(new BasicDBObject("username", "karan"));
        ArrayList<DBObject> tracks = (ArrayList<DBObject>) myUser.get("obsession");

        assertThat((String) tracks.get(0).get("media_id"), equalTo("1a13f"));
        assertThat((Integer) tracks.get(0).get("plays"), equalTo(30));

        assertThat((String) tracks.get(1).get("media_id"), equalTo("qfxh"));
        assertThat((Integer) tracks.get(1).get("plays"), equalTo(21));
    }


}
