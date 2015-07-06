package dev.zed.pishare2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import dev.zed.pishare2.view.interfaces.ILoginView;
import  dev.zed.pishare2.presenter.LoginPresenter;


    public class LoginActivity extends Activity implements ILoginView,
            View.OnClickListener {

        private ProgressBar progressBar;
        private EditText username;
        private EditText password;
        private LoginPresenter presenter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            progressBar = (ProgressBar) findViewById(R.id.progress);
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            findViewById(R.id.button).setOnClickListener(this);

            presenter = new LoginPresenter(this);
        }

        @Override
        public void showProgress() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void hideProgress() {
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void setUsernameError() {
            username.setError(getString(R.string.username_error));
        }

        @Override
        public void setPasswordError() {
            password.setError(getString(R.string.password_error));
        }

        @Override
        public void navigateToHome() {
            startActivity(new Intent(this, HomeActivity.class).putExtra("name",
                    username.getText().toString()).putExtra("pass",
                    password.getText().toString()));
            finish();
        }

        @Override
        public void onClick(View v) {
            presenter.validate(username.getText().toString(), password.getText()
                    .toString());
        }
    }
