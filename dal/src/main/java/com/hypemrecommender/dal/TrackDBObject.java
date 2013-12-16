package com.hypemrecommender.dal;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 16/12/2013
 * Time: 15:21
 */
public class TrackDBObject extends SoundcloudDBObject {
    public TrackDBObject(final CloudId cloudId, final String title, final String artist, final String streamUrl) {
        super(cloudId.getId());
        this.append("title", title);
        this.append("artist", artist);
        this.append("streamUrl", streamUrl);
    }
}
