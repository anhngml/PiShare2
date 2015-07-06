package dev.zed.pishare2.common;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import dev.zed.pishare2.listener.OnResponeListener;

/**
 * Created by Dr on 7/3/2015.
 */
public class JSONHttpClientAsyncTask<T> extends AsyncTask<String, String,Object> {
    OnResponeListener Listener;
    int RequestCode;
    List<NameValuePair> Args;
    Class<T> ObjectClass;
    public JSONHttpClientAsyncTask(List<NameValuePair> args, Class<T> objectClass, OnResponeListener listener, int requestCode) {
        Listener = listener;
        RequestCode = requestCode;
        Args = args;
        ObjectClass = objectClass;
    }

    @Override
    protected Object doInBackground(String... uri) {
        JSONHttpClient jsonHttpClient = new JSONHttpClient();
        Object result = jsonHttpClient.PostParams(uri[0], Args, ObjectClass);
        return  result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        Listener.OnResponed(result, RequestCode);
    }
}
