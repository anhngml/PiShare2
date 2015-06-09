package dev.zed.pishare2.presenter;

import java.util.ArrayList;

import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.listener.OnContentListerner;
import dev.zed.pishare2.model.NewsFeedModel;
import dev.zed.pishare2.model.UsersModel;
import dev.zed.pishare2.model.interfaces.INewsFeedModel;
import dev.zed.pishare2.model.interfaces.IUsersModel;
import dev.zed.pishare2.presenter.interfaces.IContentPresenter;
import dev.zed.pishare2.view.interfaces.IMainFragView;

/**
 * Created by Dr on 4/7/2015.
 */
public class ContentPresenter implements IContentPresenter, OnContentListerner {
    private IMainFragView mainView;
    private INewsFeedModel newsFeedModel;
    private IUsersModel usersModel;

    public ContentPresenter(IMainFragView _mainView) {
        mainView = _mainView;
        if (mainView.getType() == 0) {
            newsFeedModel = new NewsFeedModel();
//            ArrayList<BaseItem> items =
            newsFeedModel.getItems("vietanh@cic.com.vn", this);
            mainView.setItems(new ArrayList<BaseItem>());
        } else if (mainView.getType() == 1) {
            usersModel = new UsersModel();
            ArrayList<BaseItem> items = usersModel.getItems();
            mainView.setItems(new ArrayList<BaseItem>());
        }
    }

    @Override
    public void OnDatanotifyDataSetChanged(ArrayList items) {
        mainView.setItems(items);
    }
}
