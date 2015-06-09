package dev.zed.pishare2.view.interfaces;

import android.app.Fragment;

import java.util.ArrayList;

import dev.zed.pishare2.entity.BaseItem;

/**
 * Created by Dr on 4/7/2015.
 */
public interface IMainFragView {
    public int getType();
    void setItems(ArrayList<BaseItem> items);
}
