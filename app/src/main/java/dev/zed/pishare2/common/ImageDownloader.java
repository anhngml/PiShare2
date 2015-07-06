package dev.zed.pishare2.common;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

import dev.zed.pishare2.listener.OnResponeListener;

/**
 * Created by Dr on 7/2/2015.
 */
public class ImageDownloader extends AsyncTask<String, Integer, Drawable> {
    public static final int DOWNLOAD_IMAGE_REQUEST_CODE = 121;
    OnResponeListener listener;
    public  ImageDownloader(OnResponeListener _listener){
        listener = _listener;
    }
    @Override
    protected Drawable doInBackground(String... params) {
        return LoadImageFromWebOperations(params[0]);
    }

    Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        //super.onPostExecute(drawable);
        listener.OnResponed(drawable, DOWNLOAD_IMAGE_REQUEST_CODE);
    }
}
