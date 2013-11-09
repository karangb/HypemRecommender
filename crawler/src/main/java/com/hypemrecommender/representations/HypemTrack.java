package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 07/11/2013
 * Time: 20:27
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class HypemTrack {

    @JsonProperty("@media_id")
    public String mediaId;

    @JsonProperty("@artist")
    public String artist;

    @JsonProperty("@title")
    public String title;

    @SuppressWarnings("UnusedDeclaration")
    public HypemTrack()
    {
        // Do Nothing
    }

    public HypemTrack(String mediaId, String artist, String title)
    {
        this.mediaId = mediaId;
        this.artist = artist;
        this.title = title;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HypemTrack that = (HypemTrack) o;

        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        if (mediaId != null ? !mediaId.equals(that.mediaId) : that.mediaId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mediaId != null ? mediaId.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
