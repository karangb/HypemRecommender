import com.hypemrecommender.representations.Rating;
import com.hypemrecommender.representations.Track;
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
public class RatingTest {

    private Rating rating;

    @Before
    public void setUp()
    {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Melt Feat. Kilo Kish", "Chet Faker"));
        tracks.add(new Track("Think Of You RAC Mix", "MS MR"));

        rating = new Rating(tracks);
    }

    @Test
    public void serializesToJson() throws Exception
    {
        assertThat("a rating can be serialized to JSON",
                asJson(rating),
                is(equalTo(jsonFixture("fixtures/rating.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat("a Rating can be deserialized from JSON",
                fromJson(jsonFixture("fixtures/rating.json"), Rating.class),
                equalTo(rating));
    }
}
