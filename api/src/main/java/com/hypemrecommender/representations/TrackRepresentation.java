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
public class TrackRepresentation {

    @JsonProperty
    private String hypemId;

    @JsonProperty
    private String title;

    @JsonProperty
    private String artist;

    @JsonProperty
    private String streamUrl;

    @SuppressWarnings("UnusedDeclaration")
    public TrackRepresentation()
    {
        // Do Nothing
    }

    public TrackRepresentation(String hypemId, String title, String artist) {
        this.hypemId = hypemId;
        this.title = title;
        this.artist = artist;
    }

    public TrackRepresentation(final String hypemId, final String title, final String artist, final String streamUrl) {
        this.hypemId = hypemId;
        this.title = title;
        this.artist = artist;
        this.streamUrl = streamUrl;
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

        final TrackRepresentation trackRepresentation = (TrackRepresentation) o;

        if (artist != null ? !artist.equals(trackRepresentation.artist) : trackRepresentation.artist != null) return false;
        if (hypemId != null ? !hypemId.equals(trackRepresentation.hypemId) : trackRepresentation.hypemId != null) return false;
        if (title != null ? !title.equals(trackRepresentation.title) : trackRepresentation.title != null) return false;

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
