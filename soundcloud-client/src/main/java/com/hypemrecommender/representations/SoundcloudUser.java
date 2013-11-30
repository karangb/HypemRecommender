package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: jamal
 * Date: 11/30/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SoundcloudUser  {

    @JsonProperty
    private int id;

    public void setId(int id){
        this.id = id;
    }

    public String getId(){
        return String.valueOf(id);
    }
}
