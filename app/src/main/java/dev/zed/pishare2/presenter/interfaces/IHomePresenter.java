package dev.zed.pishare2.presenter.interfaces;

import android.view.MenuItem;

/**
 * Created by Dr on 4/7/2015.
 */
public interface IHomePresenter {
    public void onResume();
    public void onNavDrawerItemClicked(int position);
    void onMenuItemClicked(MenuItem item);
}
