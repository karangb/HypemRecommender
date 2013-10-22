package com.hypemrecommender.representations;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:01
 */
public class RecommendationTest {

    private Recommendation recommendation;

    @Before
    public void setUp()
    {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("1yhd0", "Melt Feat. Kilo Kish", "Chet Faker"));
        tracks.add(new Track("1yy7c", "Think Of You RAC Mix", "MS MR"));

        recommendation = new Recommendation(tracks);
    }

    @Test
    public void serializesToJson() throws Exception
    {
        assertThat("a recommendation can be serialized to JSON",
                asJson(recommendation),
                is(equalTo(jsonFixture("fixtures/recommendation.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat("a Recommendation can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/recommendation.json"), Recommendation.class),
                equalTo(recommendation));
    }
}
