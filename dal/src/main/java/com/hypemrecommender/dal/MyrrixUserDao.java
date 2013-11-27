package com.hypemrecommender.dal;

import com.hypemrecommender.blogapi.CloudTrack;
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


    private final TrackDao trackDao;
    private final DBCollection userCollection;

    public MyrrixUserDao(final TrackDao trackDao, final DBCollection userCollection) {
        this.trackDao = trackDao;
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

            if(!trackDao.exists(track))
            {
                trackDao.provision(track);
            }
        }
        DBObject userDoc = BasicDBObjectBuilder.start().add("username", userRepresentation.getName()).add("obsession", tracks).get();
        userCollection.insert(userDoc);
    }

    @Override
    public void provisionFavourite(final CloudTrack track) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
