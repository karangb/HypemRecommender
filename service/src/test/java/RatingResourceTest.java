
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:19
 */
public class RatingResourceTest extends ResourceTest {
    private final Track track1 = new Track("track1", "artist1");
    private final Track track2 = new Track("track2", "artist2");
    private final RatingDAO ratingDao = mock(RatingDAO.class);
    
    private List<Track> tracks;
    private Rating rating;


    @Before
    public void setUp()
    {
        tracks = new ArrayList<Track>();
        tracks.add(track1);
        tracks.add(track2);
        rating = new Rating(tracks);
        when(ratingDao.getRating("karan")).thenReturn(rating);
    }

    @Override
    protected void setUpResources() throws Exception {
        addResource(new RatingResource(ratingDao));
    }

    @Test
    public void testRatingsGetsTracks() throws Exception{
        assertThat(client().resource("/ratings").queryParam("username", "karan").get(Rating.class)).isEqualTo(rating);
        verify(ratingDao).getRating("karan");
    }

}
