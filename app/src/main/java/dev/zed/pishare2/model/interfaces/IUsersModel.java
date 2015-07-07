package dev.zed.pishare2.model.interfaces;

import java.util.ArrayList;

import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.listener.OnContentListerner;

/**
 * Created by Dr on 4/8/2015.
 */
public interface IUsersModel {
    void getItems(String ownerEmail, OnContentListerner listerner);
    void search(String ownerEmail, String query, OnContentListerner listerner);
}
