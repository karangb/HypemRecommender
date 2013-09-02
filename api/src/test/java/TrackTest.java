import org.junit.Test;

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


public class TrackTest {

    @Test
    public void serializesToJson() throws Exception
    {
        final Track track = new Track("Melt Feat. Kilo Kish", "Chet Faker");
        assertThat("a Track can be serialized to JSON",
                asJson(track),
                is(equalTo(jsonFixture("fixtures/track.json"))));
    }
}
