package com.hypemrecommender.dal;

import com.mongodb.BasicDBObject;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 15/12/2013
 * Time: 20:30
 */
public class SoundcloudDBObject extends BasicDBObject {
    public SoundcloudDBObject(final int soundcloudId) {
        super();
        this.append("soundcloudId", soundcloudId);
    }
}
