import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:03
 */
public class Rating {

    @JsonProperty
    private List<Track> tracks;

    public Rating(List<Track> tracks) {
        this.tracks = tracks;
    }

    @SuppressWarnings("UnusedDeclaration")
    public List<Track> getTracks() {
        return tracks;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
