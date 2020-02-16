package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    @ColumnInfo
    String name;

    // normally this field would be annotated @PrimaryKey because this is an embedded object
    // it is not needed
    @ColumnInfo
    Long twitter_id;

    // Parse model from JSON
    public User parseJSON(JSONObject tweetJson) throws JSONException {

        User user = new User();
        this.twitter_id = tweetJson.getLong("id");
        this.name = tweetJson.getString("name");
        return user;
    }
}

