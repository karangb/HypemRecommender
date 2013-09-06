import com.hypemrecommender.representations.Rating;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:25
 */
public interface RatingDAO {
    public Rating getRating(String username);
}
