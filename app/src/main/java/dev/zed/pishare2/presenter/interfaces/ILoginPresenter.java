package dev.zed.pishare2.presenter.interfaces;

/**
 * Created by Dr on 6/26/2015.
 */
public interface ILoginPresenter {
    public void onResume();
    public void validate(String username, String password);
}
