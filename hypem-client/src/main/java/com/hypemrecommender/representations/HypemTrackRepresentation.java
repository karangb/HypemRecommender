package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hypemrecommender.Visitable;
import com.hypemrecommender.Visitor;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 07/11/2013
 * Time: 20:27
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class HypemTrackRepresentation implements Visitable {

    @JsonProperty("@media_id")
    public String mediaId;

    @JsonProperty("@artist")
    public String artist;

    @JsonProperty("@title")
    public String title;

    @JsonProperty("@thumb_url")
    public String thumbUrl;

    @JsonProperty("@thumb_url_medium")
    public String thumbUrlMedium;

    @JsonProperty("@thumb_url_large")
    public String thumbUrlLarge;

    @JsonProperty("@plays")
    public int plays;

    @SuppressWarnings("UnusedDeclaration")
    public HypemTrackRepresentation()
    {
        // Do Nothing
    }

    public HypemTrackRepresentation(String mediaId,
                                    String artist,
                                    String title,
                                    int plays,
                                    String thumbUrl,
                                    String thumbUrlMedium,
                                    String thumbUrlLarge)
    {
        this.mediaId = mediaId;
        this.artist = artist;
        this.title = title;
        this.plays = plays;
        this.thumbUrl = thumbUrl;
        this.thumbUrlMedium = thumbUrlMedium;
        this.thumbUrlLarge = thumbUrlLarge;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(final String mediaId) {
        this.mediaId = mediaId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(final String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getThumbUrlMedium() {
        return thumbUrlMedium;
    }

    public void setThumbUrlMedium(final String thumbUrlMedium) {
        this.thumbUrlMedium = thumbUrlMedium;
    }

    public String getThumbUrlLarge() {
        return thumbUrlLarge;
    }

    public void setThumbUrlLarge(final String thumbUrlLarge) {
        this.thumbUrlLarge = thumbUrlLarge;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(final int plays) {
        this.plays = plays;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HypemTrackRepresentation that = (HypemTrackRepresentation) o;
        if (plays != that.plays) return false;
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        if (mediaId != null ? !mediaId.equals(that.mediaId) : that.mediaId != null) return false;
        if (thumbUrl != null ? !thumbUrl.equals(that.thumbUrl) : that.thumbUrl != null) return false;
        if (thumbUrlLarge != null ? !thumbUrlLarge.equals(that.thumbUrlLarge) : that.thumbUrlLarge != null)
            return false;
        if (thumbUrlMedium != null ? !thumbUrlMedium.equals(that.thumbUrlMedium) : that.thumbUrlMedium != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = mediaId != null ? mediaId.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (thumbUrl != null ? thumbUrl.hashCode() : 0);
        result = 31 * result + (thumbUrlMedium != null ? thumbUrlMedium.hashCode() : 0);
        result = 31 * result + (thumbUrlLarge != null ? thumbUrlLarge.hashCode() : 0);
        result = 31 * result + plays;
        return result;
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
