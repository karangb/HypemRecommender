import com.hypemrecommender.representations.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 07/11/2013
 * Time: 20:49
 */
public class HypemUserTest {

    @Test
    public void deserializesFromJSON() throws Exception {
        final HypemTrackRepresentation track1 = new HypemTrackRepresentation("1a13f", "Parov Stelar", "The Golden Boy", 30, "http://static-ak.hypem.net/thumbs_new/25/1434405.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_120.jpg", "http://static-ak.hypem.net/thumbs_new/25/1434405_320.jpg");
        final HypemTrackRepresentation track2 = new HypemTrackRepresentation("qfxh", "Caravan Palace", "Jolie Coquine", 21, "http://static-ak.hypem.net/thumbs_new/4c/2267468.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_120.jpg", "http://static-ak.hypem.net/thumbs_new/4c/2267468_320.jpg");

        List<HypemTrackRepresentation> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        final ObsessedRepresentation obsessed = new ObsessedRepresentation(tracks);

        final HypemUserRepresentation expectedHypemUser = new HypemUserRepresentation("karan", obsessed);

        assertThat("a HypemUserRepresentation can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/hypemUser.json"), HypemUserRepresentation.class),
                equalTo(expectedHypemUser));
    }
}
