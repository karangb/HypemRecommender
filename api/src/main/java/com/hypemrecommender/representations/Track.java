package com.hypemrecommender.representations;

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
    private String hypemId;

    @JsonProperty
    private String title;

    @JsonProperty
    private String artist;

    @SuppressWarnings("UnusedDeclaration")
    public Track()
    {
        // Do Nothing
    }

    public Track(String hypemId, String title, String artist) {
        this.hypemId = hypemId;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Track track = (Track) o;

        if (artist != null ? !artist.equals(track.artist) : track.artist != null) return false;
        if (hypemId != null ? !hypemId.equals(track.hypemId) : track.hypemId != null) return false;
        if (title != null ? !title.equals(track.title) : track.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hypemId != null ? hypemId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }
}
