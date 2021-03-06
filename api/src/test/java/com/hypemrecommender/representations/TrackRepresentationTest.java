package com.hypemrecommender.representations;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.yammer.dropwizard.testing.JsonHelpers.*;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 02/09/2013
 * Time: 23:30
 */


public class TrackRepresentationTest {

    @Test
    public void serializesToJson() throws Exception
    {
        final TrackRepresentation trackRepresentation = new TrackRepresentation("1yhd0", "Melt Feat. Kilo Kish", "Chet Faker");
        assertThat("a Track can be serialized to JSON",
                asJson(trackRepresentation),
                is(equalTo(jsonFixture("fixtures/track.json"))));
    }

    @Test
    public void serializesWithStreamUrl() throws IOException {
        final TrackRepresentation trackRepresentation = new TrackRepresentation("1yhd0", "Melt Feat. Kilo Kish", "Chet Faker", "http://someStreamUrl");
        assertThat("a Track can be serialized to JSON",
                asJson(trackRepresentation),
                is(equalTo(jsonFixture("fixtures/trackWithStreamUrl.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final TrackRepresentation trackRepresentation = new TrackRepresentation("1yhd0", "Melt Feat. Kilo Kish", "Chet Faker");
        assertThat("a Track can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/track.json"), TrackRepresentation.class),
                equalTo(trackRepresentation));
    }

    @Test
    public void deserializesWithStreamUrl() throws Exception {
        final TrackRepresentation trackRepresentation = new TrackRepresentation("1yhd0", "Melt Feat. Kilo Kish", "Chet Faker", "http://someStreamUrl");
        assertThat("a Track can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/trackWithStreamUrl.json"), TrackRepresentation.class),
                equalTo(trackRepresentation));
    }
}
