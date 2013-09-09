package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/09/2013
 * Time: 23:03
 */
public class Recommendation {

    @JsonProperty
    private List<Track> tracks;

    @SuppressWarnings("UnusedDeclaration")
    public Recommendation()
    {
        // Do Nothing
    }

    public Recommendation(List<Track> tracks) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommendation recommendation = (Recommendation) o;

        return (!(tracks != null ? !tracks.equals(recommendation.tracks) : recommendation.tracks != null));
    }

    @Override
    public int hashCode() {
        return tracks != null ? tracks.hashCode() : 0;
    }
}
