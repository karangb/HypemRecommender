import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 04/09/2013
 * Time: 22:35
 */
@Path("/ratings")
@Produces(MediaType.APPLICATION_JSON)
public class RatingResource {
    private RatingDAO ratingDao;

    public RatingResource(RatingDAO ratingDao) {
        this.ratingDao = ratingDao;
    }

    @GET
    @Timed
    public Rating getRating(@QueryParam("username") String username)  {
        return ratingDao.getRating(username);
    }
}
