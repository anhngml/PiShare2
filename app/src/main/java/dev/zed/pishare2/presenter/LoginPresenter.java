package dev.zed.pishare2.presenter;

import dev.zed.pishare2.listener.OnLoginListener;
import dev.zed.pishare2.model.LoginModel;
import dev.zed.pishare2.model.interfaces.ILoginModel;
import dev.zed.pishare2.presenter.interfaces.ILoginPresenter;
import dev.zed.pishare2.view.interfaces.ILoginView;

/**
 * Created by Dr on 6/26/2015.
 */
public class LoginPresenter implements ILoginPresenter, OnLoginListener {
    private ILoginView loginView;
    private ILoginModel loginModel;

    public LoginPresenter(ILoginView loginView) {

        this.loginView = loginView;
        this.loginModel = new LoginModel();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.daonho.androidmvp.presenter.ILoginPresenter#validate(java.lang.String
     * , java.lang.String)
     */



    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void validate(String username, String password) {
        loginView.showProgress();
        loginModel.login(username, password, this);
    }
}
