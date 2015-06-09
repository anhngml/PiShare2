package dev.zed.pishare2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;
import dev.zed.pishare2.presenter.HomePresenter;
import dev.zed.pishare2.presenter.interfaces.IHomePresenter;
import dev.zed.pishare2.view.interfaces.IHomeView;


public class HomeActivity extends NavigationLiveo implements NavigationLiveoListener, IHomeView {
    //    private List<String> mListNameItem;
    private IHomePresenter presenter;

    @Override
    public void onUserInformation() {
//        this.mUserName.setText("Viet Anh");
//        this.mUserEmail.setText("en.vietanh@gmail.com");
//        this.mUserPhoto.setImageResource(R.drawable.ic_rudsonlive);
//        this.mUserBackground.setImageResource(R.drawable.ic_user_background);
//        <!--If you want to create your own user header just do the following-->//
//        View mCustomHeader = getLayoutInflater().inflate(R.layout.custom_header_user, this.getListView(), false);
//        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);
//        this.addCustomHeader(mCustomHeader); //This will add the new header and remove the default user header
    }

    @Override
    public void onInt(Bundle bundle) {
        this.setNavigationListener(this);
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
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {
        switch (position) {
            case 0:
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;
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
    public void setUserInfo(String username, String email, Bitmap avatar) {
        this.mUserName.setText(username);
        this.mUserEmail.setText(email);
        if (avatar != null)
            this.mUserPhoto.setImageBitmap(avatar);
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

}
