import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
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
public class RatingTest {
    @Test
    public void serializesToJson() throws Exception
    {
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("Melt Feat. Kilo Kish", "Chet Faker"));
        tracks.add(new Track("Think Of You RAC Mix", "MS MR"));

        final Rating rating = new Rating(tracks);

        assertThat("a rating can be serialized to JSON",
                asJson(rating),
                is(equalTo(jsonFixture("fixtures/rating.json"))));
    }
}
