package com.hypemrecommender.dal;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 01:35
 */
public class MyrrixUserDao implements UserDao {


    private final DBCollection userCollection;

    public MyrrixUserDao(final DBCollection userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public long getUserId(final String username) {
        return 0;
    }

    @Override
    public boolean exists(final String username) {
        return false;
    }

    @Override
    public void provision(final HypemUserRepresentation userRepresentation) {
        List<DBObject> tracks = new ArrayList<>();
        for(HypemTrackRepresentation track : userRepresentation.obsessed.getTracks())
        {
            DBObject trackDoc = BasicDBObjectBuilder.start().add("media_id", track.getMediaId()).
                                                        add("plays", track.getPlays()).get();
            tracks.add(trackDoc);
        }
        DBObject userDoc = BasicDBObjectBuilder.start().add("username", userRepresentation.getName()).add("obsession", tracks).get();
        userCollection.insert(userDoc);
    }
}
