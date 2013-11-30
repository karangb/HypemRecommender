package com.hypemrecommender.representations;

import org.junit.Test;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jamal
 * Date: 11/30/13
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class SoundcloudUserCollectionTest {
    @Test
    public void deserializesFromJSON() throws Exception {
        SoundcloudUser[] users = fromJson(jsonFixture("fixtures/users.json"), SoundcloudUser[].class);
        assertThat(users[0].getId(), equalTo("39161588"));
        assertThat(users[1].getId(), equalTo("1293811"));
    }


}
