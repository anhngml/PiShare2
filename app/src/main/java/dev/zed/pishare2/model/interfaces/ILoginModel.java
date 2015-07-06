package dev.zed.pishare2.model.interfaces;

import dev.zed.pishare2.listener.OnLoginListener;

/**
 * Created by Dr on 6/28/2015.
 */
public interface ILoginModel {

    public void login(String username, String password, OnLoginListener listener);
}
