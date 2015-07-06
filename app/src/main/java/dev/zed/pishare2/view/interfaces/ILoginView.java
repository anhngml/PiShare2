package dev.zed.pishare2.view.interfaces;

/**
 * Created by Dr on 6/28/2015.
 */
public interface ILoginView {

    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void navigateToHome();
}
