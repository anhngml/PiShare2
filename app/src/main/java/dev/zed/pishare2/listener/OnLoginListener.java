package dev.zed.pishare2.listener;

/**
 * Created by Dr on 6/28/2015.
 */
public interface OnLoginListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();
}
