package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 07/11/2013
 * Time: 20:56
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Obsessed {
    @JsonProperty("@tracks")
    public List<HypemTrackRepresentation> tracks;

    public Obsessed()
    {
        //EMPTY
    }

    public Obsessed(final List<HypemTrackRepresentation> tracks)
    {
        this.tracks = tracks;
    }

    public List<HypemTrackRepresentation> getTracks() {
        return tracks;
    }

    public void setTracks(final List<HypemTrackRepresentation> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Obsessed obsessed = (Obsessed) o;

        return !(tracks != null ? !tracks.equals(obsessed.tracks) : obsessed.tracks != null);

    }

    @Override
    public int hashCode() {
        return tracks != null ? tracks.hashCode() : 0;
    }
}
