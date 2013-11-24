package com.hypemrecommender.representations;

import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 24/11/2013
 * Time: 17:54
 */
public class UserFavouritesTest {
    @Test
    public void deserializesFromJSON() throws Exception {
        SoundcloudTrack[] tracks = fromJson(jsonFixture("fixtures/userFavourites.json"), SoundcloudTrack[].class);

        assertThat(tracks[0].getId(), equalTo("46192720"));
        assertThat(tracks[0].getTitle(), equalTo("Foley Moley (Virtual 3D Audio)"));
        assertThat(tracks[0].getArtist(), equalTo("PogoMix"));
        assertThat(tracks[0].getStreamUrl(), equalTo("http://api.soundcloud.com/tracks/46192720/stream"));

        assertThat(tracks[1].getId(), equalTo("61369017"));
        assertThat(tracks[1].getTitle(), equalTo("Her Tea Leaves"));
        assertThat(tracks[1].getArtist(), equalTo("Iron and Wine"));
        assertThat(tracks[1].getStreamUrl(), equalTo("http://api.soundcloud.com/tracks/61369017/stream"));
    }
}
