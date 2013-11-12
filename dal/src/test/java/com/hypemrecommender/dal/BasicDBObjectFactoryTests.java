package com.hypemrecommender.dal;

import com.mongodb.DBObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 12/11/2013
 * Time: 21:01
 */
public class BasicDBObjectFactoryTests {
    @Test
    public void testCreateTrack()
    {
        DBObject expectedDoc = FakeRepresentations.track1Doc();
        BasicDBObjectFactory factory = new BasicDBObjectFactory();

        DBObject doc = factory.createTrack(FakeRepresentations.track1());
        assertThat(doc, equalTo(expectedDoc));
    }
}
