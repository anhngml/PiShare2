package dev.zed.pishare2.presenter;

import android.support.v4.app.Fragment;

import dev.zed.pishare2.R;
import dev.zed.pishare2.listener.OnHomeListerner;
import dev.zed.pishare2.model.ModuleModel;
import dev.zed.pishare2.model.NewsFeedModel;
import dev.zed.pishare2.model.interfaces.IModuleModel;
import dev.zed.pishare2.model.interfaces.INewsFeedModel;
import dev.zed.pishare2.presenter.interfaces.IHomePresenter;
import dev.zed.pishare2.view.MainFragment;
import dev.zed.pishare2.view.interfaces.IHomeView;
import dev.zed.pishare2.view.interfaces.IMainFragView;

/**
 * Created by Dr on 4/7/2015.
 */
public class HomePresenter implements IHomePresenter, OnHomeListerner {
    private IHomeView homeView;
    private IModuleModel modules;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
        this.modules = new ModuleModel();
        homeView.setNavDrawerItems(modules.getModuleList(), modules.getModuleIconList(), modules.getModuleSparseCounter());
        homeView.setFooterDrawer(R.string.logout, R.drawable.ic_launcher);
        homeView.setToolbarElev(0);
        MainFragment fragment = new MainFragment().newInstance(modules.getModuleList().get(0), 0);
        homeView.setCurrentFragment(fragment);

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onNavDrawerItemClicked(int position) {
        Fragment fragment;
        fragment = new MainFragment().newInstance(modules.getModuleList().get(position), position);
        homeView.setCurrentFragment(fragment);
    }

    @Override
    public void onMenuItemClicked(String id) {
        if(id.equals("add")){
            homeView.pickImage();
        }else if(id.equals("search"))
        {
            homeView.searchUser();
        }
    }
}
