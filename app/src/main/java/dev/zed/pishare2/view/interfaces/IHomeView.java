package dev.zed.pishare2.view.interfaces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.SparseIntArray;
import android.view.MenuItem;

import java.util.List;

/**
 * Created by Dr on 4/7/2015.
 */
public interface IHomeView {
    public void setUserInfo(String username, String email, Drawable avatar);
    public void setNavDrawerItems(List<String> items, List<Integer> icons, SparseIntArray counters);
    public void setFooterDrawer(int title, int iconIndex);
    public void setToolbarElev(int value);
    public void setCurrentFragment(Fragment fragment);
    public void pickImage();
    void startSearchUser(MenuItem searchView);
}
