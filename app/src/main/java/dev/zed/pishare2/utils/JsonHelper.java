package dev.zed.pishare2.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import dev.zed.pishare2.entity.FeedItem;
import dev.zed.pishare2.entity.UserItem;

/**
 * Created by Dr on 4/8/2015.
 */
public class JsonHelper {
    public static void parseJsonFeed(JSONObject response,List<FeedItem> feedItems) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getString("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseJsonFeedArray(JSONArray response,List<FeedItem> feedItems) {
        try {
            //JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < response.length(); i++) {
                JSONObject feedObj = (JSONObject) response.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getString("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseJsonUserArray(JSONArray response,List<UserItem> userItems) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject userdObj = (JSONObject) response.get(i);

                UserItem item = new UserItem();
                item.setId(userdObj.getString("Id"));
                item.setName(userdObj.getString("Name"));
                item.setEmail(userdObj.getString("Email"));
                item.setLocation(userdObj.getString("Location"));
                // Image might be null sometimes
                String image = userdObj.isNull("Avatar") ? null : userdObj
                        .getString("Avatar");
                item.setProfilePic(image);
                userItems.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
