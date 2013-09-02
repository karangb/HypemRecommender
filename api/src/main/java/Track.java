import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: krg07
 * Date: 02/09/2013
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("ALL")
public class Track {
    @JsonProperty
    private String title;

    @JsonProperty
    private String artist;

    public Track(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
