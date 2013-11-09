import com.hypemrecommender.representations.HypemTrackRepresentation;
import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 09/11/2013
 * Time: 23:24
 */
public class HypemTrackTests {
    @Test
    public void deserializesFromJSON() throws Exception {
        final HypemTrackRepresentation expectedHypemTrack = new HypemTrackRepresentation("1a13f", "Parov Stelar", "The Golden Boy", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");

        HypemTrackRepresentation actual = fromJson(jsonFixture("fixtures/hypemTrack.json"), HypemTrackRepresentation.class);

        assertThat("a HypemUserRepresentation can be deserialized from JSON",
                actual,
                equalTo(expectedHypemTrack));
    }
}
