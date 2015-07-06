package dev.zed.pishare2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;
import dev.zed.pishare2.common.App;
import dev.zed.pishare2.common.Config;
import dev.zed.pishare2.common.ImageDownloader;
import dev.zed.pishare2.common.ImagePicker;
import dev.zed.pishare2.common.JSONHttpClient;
import dev.zed.pishare2.common.JSONHttpClientAsyncTask;
import dev.zed.pishare2.common.md5;
import dev.zed.pishare2.common.zHttpRequest;
import dev.zed.pishare2.entity.Status;
import dev.zed.pishare2.listener.OnResponeListener;
import dev.zed.pishare2.presenter.HomePresenter;
import dev.zed.pishare2.presenter.interfaces.IHomePresenter;
import dev.zed.pishare2.view.interfaces.IHomeView;


public class HomeActivity extends NavigationLiveo implements NavigationLiveoListener, IHomeView, OnResponeListener {
    //    private List<String> mListNameItem;
    private IHomePresenter presenter;
    private String curUser;
    private String curEmail;

    @Override
    public void onUserInformation() {
    }

    @Override
    public void onInt(Bundle bundle) {
        this.setNavigationListener(this);
        Intent intent = this.getIntent();
        String email = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        String URL = Config.Server_Url + "/acount/GetUserInfor?id=";
        String url = URL + email + "&" + "pwd=" + md5.md5(pass);
        AsyncTask<String, String, String> resp = new zHttpRequest(this, 0).execute(url);
        setUserInfo("", email, null);

        presenter = new HomePresenter(this);
        this.setDefaultStartPositionNavigation(0);
    }

    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {
        presenter.onNavDrawerItemClicked(position);
    }

    @Override
    public void setCurrentFragment(Fragment fragment) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        if (fragment != null) {
            mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }



    @Override
    public void pickImage() {
        //Mở Activity với REQUEST_CODE_INPUT
        Intent intent = new Intent(HomeActivity.this, ImagePicker.class);
        //gọi startActivityForResult
        startActivityForResult(intent, Config.PICKIMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        String comment = data.getStringExtra("comment");
        String image = data.getStringExtra("image");
        if (image == null) return;
        new PostImage(comment, image, Config.Server_Url).execute();
    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {
        switch (position) {
            case 0:
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add:
                presenter.onMenuItemClicked("add");
                return true;
            case R.id.menu_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClickFooterItemNavigation(View view) {
        Toast.makeText(this, R.string.open_settings, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickUserPhotoNavigation(View view) {
        Toast.makeText(this, R.string.open_user_profile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserInfo(String username, String email, Drawable avatar) {
        this.mUserName.setText(username);
        this.mUserEmail.setText(email);
        App.CurUser = email;
        if (avatar != null)
            this.mUserPhoto.setImageDrawable(avatar);
        this.mUserBackground.setImageResource(R.drawable.ic_user_background);
    }

    @Override
    public void setNavDrawerItems(List<String> items, List<Integer> icons, SparseIntArray counters) {
//        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);
        this.setNavigationAdapter(items, icons, null, counters); //mListHeaderItem
    }

    @Override
    public void setFooterDrawer(int title, int iconIndex) {
        this.setFooterInformationDrawer(title, iconIndex);
    }

    @Override
    public void setToolbarElev(int value) {
        this.setElevationToolBar(value);
    }


    @Override
    public void OnResponed(String result, int RequestCode) {
        String[] separated = result.split("&");
        curEmail = separated[0].replace("email=", "");
        curUser = separated[1].replace("name=", "");
        String avatar = separated[2].replace("avatar=", "");
        new ImageDownloader(this).execute(avatar);
    }

    @Override
    public void OnResponed(Drawable result, int RequestCode) {
        setUserInfo(curUser, curEmail, result);
    }

    @Override
    public <T> void OnResponed(T result, int RequestCode) {

    }

    class PostImage extends AsyncTask<String, String, String> {
        String status;
        String imgStr;
        String Url;
        public PostImage(String stats, String imageStr, String url){
            status = stats;
            imgStr = imageStr;
            Url = url;
        }
        @Override
        protected void onPostExecute(String s) {}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String statusUrl = Url + "api/Status/";
            String imageUrl = Url + "api/images/";
            List<NameValuePair> args = new ArrayList<NameValuePair>();
            args.add(new BasicNameValuePair("ownerEmail", curEmail));
            args.add(new BasicNameValuePair("status", status));

            dev.zed.pishare2.entity.Status sts = new dev.zed.pishare2.entity.Status();
            sts.setownerEmail(curEmail);
            sts.setstatus(status);

            JSONHttpClient jsonHttpClient = new JSONHttpClient();
            String result = jsonHttpClient.
                    PostObject(statusUrl, sts);
            if(result == null) return  null;

            result = result.replace("\n", "").replace("\"", "");

            jsonHttpClient = new JSONHttpClient();

            String res = jsonHttpClient.PostFile(imageUrl, result, imgStr, String.class);
            presenter.onNavDrawerItemClicked(0);
            return null;
        }
    }
}

