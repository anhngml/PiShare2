package dev.zed.pishare2.listener;

import android.graphics.drawable.Drawable;

/**
 * Created by Dr on 7/1/2015.
 */
public interface OnResponeListener {

    void OnResponed(String result, int RequestCode);
    void OnResponed(Drawable result, int RequestCode);
    <T> void OnResponed(T result, int RequestCode);
}
