package dev.zed.pishare2.model;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

import dev.zed.pishare2.common.Config;
import dev.zed.pishare2.common.md5;
import dev.zed.pishare2.common.zHttpRequest;
import dev.zed.pishare2.listener.OnLoginListener;
import dev.zed.pishare2.listener.OnResponeListener;
import dev.zed.pishare2.model.interfaces.ILoginModel;

/**
 * Created by Dr on 6/28/2015.
 */
public class LoginModel implements ILoginModel, OnResponeListener {
    String URL = Config.Server_Url + "/acount/signin?id=";
    OnLoginListener listener;

    @Override
    public void login(final String username, final String password, final OnLoginListener _listener) {
        listener = _listener;
        login(username, password, this);
    }

    void login(final String username, final String password, final OnResponeListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)) {
                    listener.OnResponed("username is empty", Config.LOGIN_REQUEST_CODE);
                    error = true;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.OnResponed("password is empty", Config.LOGIN_REQUEST_CODE);
                    error = true;
                }
                if (!error) {
                    try {
                        String url = URL + username + "&" + "pwd=" + md5.md5(password);
                        AsyncTask<String, String, String> resp = new zHttpRequest(listener, Config.LOGIN_REQUEST_CODE).execute(url);
                    } catch (Exception e) {
                        listener.OnResponed(e.getMessage(), Config.LOGIN_REQUEST_CODE);
                    }
                }
            }
        }, 0);
    }

    @Override
    public void OnResponed(String result, int RequestCode) {
        if (RequestCode == Config.LOGIN_REQUEST_CODE)
            if (result != null && result.equals("Success")) {
                listener.onSuccess();
            } else
                listener.onPasswordError();
    }

    @Override
    public void OnResponed(Drawable result, int RequestCode) {

    }

    @Override
    public <T> void OnResponed(T result, int RequestCode) {

    }
}
