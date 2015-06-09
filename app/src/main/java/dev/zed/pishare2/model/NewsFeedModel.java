package dev.zed.pishare2.model;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import dev.zed.pishare2.HomeActivity;
import dev.zed.pishare2.common.App;
import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.entity.FeedItem;
import dev.zed.pishare2.listener.OnContentListerner;
import dev.zed.pishare2.model.interfaces.INewsFeedModel;
import dev.zed.pishare2.utils.JsonHelper;

/**
 * Created by Dr on 4/7/2015.
 */
public class NewsFeedModel implements INewsFeedModel {
    private String URL_FEED = "http://10.0.0.67:81/api/feed?email=";// "http://api.androidhive.info/feed/feed.json";
    private static final String TAG = HomeActivity.class.getSimpleName();
    ArrayList<FeedItem> feedItems = new ArrayList<FeedItem>();
    private OnContentListerner listerner;
    @Override
    public void getItems(String ownerEmail, OnContentListerner _listerner) {

        listerner = _listerner;
        Cache cache = App.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED + ownerEmail);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    //JsonHelper.parseJsonFeed(new JSONObject(data), feedItems);
                    JsonHelper.parseJsonFeedArray(new JSONArray(data), feedItems);
                    listerner.OnDatanotifyDataSetChanged(feedItems);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    URL_FEED + ownerEmail, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    //VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        JsonHelper.parseJsonFeedArray(response, feedItems);
                        listerner.OnDatanotifyDataSetChanged(feedItems);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });



//            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
//                    URL_FEED + ownerEmail, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//                    VolleyLog.d(TAG, "Response: " + response.toString());
//                    if (response != null) {
//                        JsonHelper.parseJsonFeed(response, feedItems);
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                }
//            });

            // Adding request to volley request queue
            App.getInstance().addToRequestQueue(jsonArrayRequest);
        }
//        return feedItems;
    }
}
