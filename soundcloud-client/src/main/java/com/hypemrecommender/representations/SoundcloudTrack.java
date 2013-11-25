package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hypemrecommender.blogapi.CloudTrack;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 24/11/2013
 * Time: 18:15
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SoundcloudTrack implements CloudTrack{
    @JsonProperty
    private int id;

    @JsonProperty
    private String title;

    @JsonProperty
    private Map<String, Object> user;

    @JsonProperty("stream_url")
    private String streamUrl;

    @Override
    public Collection<String> getFavouriters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String getArtist() {
        return (String) user.get("username");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getStreamUrl() {
        return streamUrl;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setArtist(Map<String, Object> user) {
        this.user = user;
    }

    public void setStreamUrl(final String streamUrl) {
        this.streamUrl = streamUrl;
    }
}
