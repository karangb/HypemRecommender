package com.hypemrecommender.models;

import com.hypemrecommender.dal.CloudId;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 03/11/2013
 * Time: 23:25
 */
public interface UserRepository {
    User createUser(CloudId cloudUserId);

    boolean exists(CloudId cloudUserId);

    User getUser(CloudId cloudUserId);
}
