package dev.zed.pishare2.model;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import dev.zed.pishare2.HomeActivity;
import dev.zed.pishare2.common.App;
import dev.zed.pishare2.common.Config;
import dev.zed.pishare2.entity.FeedItem;
import dev.zed.pishare2.entity.UserItem;
import dev.zed.pishare2.listener.OnContentListerner;
import dev.zed.pishare2.model.interfaces.IUsersModel;
import dev.zed.pishare2.utils.JsonHelper;

/**
 * Created by Dr on 4/8/2015.
 */
public class UsersModel implements IUsersModel {
    private static final String TAG = HomeActivity.class.getSimpleName();
    ArrayList<UserItem> userItems = new ArrayList<>();
    private OnContentListerner listerner;

    @Override
    public void getItems(String ownerEmail, OnContentListerner _listerner) {
        listerner = _listerner;
        Cache cache = App.getInstance().getRequestQueue().getCache();
        String URL_FEED = Config.Server_Url + "/api/friends?email=";
        Cache.Entry entry = cache.get(URL_FEED + ownerEmail);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    //JsonHelper.parseJsonFeed(new JSONObject(data), feedItems);
                    JsonHelper.parseJsonUserArray(new JSONArray(data), userItems);
                    listerner.OnDatanotifyDataSetChanged(userItems);
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
                        JsonHelper.parseJsonUserArray(response, userItems);
                        listerner.OnDatanotifyDataSetChanged(userItems);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });
            App.getInstance().addToRequestQueue(jsonArrayRequest);
        }
    }
}
