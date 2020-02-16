package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.codepath.apps.restclienttemplate.models.User.parseJSON;

@Entity
public class Tweet {
  // Define database columns and associated fields
  @PrimaryKey
  @ColumnInfo
  Long id;
  @ColumnInfo
  String userHandle;
  @ColumnInfo
  String timestamp;
  @ColumnInfo
  String body;

  // Use @Embedded to keep the column entries as part of the same table while still
  // keeping the logical separation between the two objects.
  @Embedded
  User user;


  // Add a constructor that creates an object from the JSON response
  public Tweet(JSONObject object){
    try {
      this.user = parseJSON(object.getJSONObject("user"));
      this.userHandle = object.getString("user_username");
      this.timestamp = object.getString("timestamp");
      this.body = object.getString("body");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
    ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

    for (int i=0; i < jsonArray.length(); i++) {
        JSONObject tweetJson = null;
        try {
            tweetJson = jsonArray.getJSONObject(i);
        } catch (Exception e) {
            e.printStackTrace();
            continue;
        }

        Tweet tweet = new Tweet(tweetJson);
        tweets.add(tweet);
    }

    return tweets;
  }
}



