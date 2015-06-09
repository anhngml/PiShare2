package dev.zed.pishare2.model;

import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.zed.pishare2.R;
import dev.zed.pishare2.model.interfaces.IModuleModel;

/**
 * Created by Dr on 4/7/2015.
 */
public class ModuleModel implements IModuleModel {

    @Override
    public List<String> getModuleList() {
        return Arrays.asList(
                "NewsFeed",
                "Friends",
                "Settings"
        );
    }

    @Override
    public List<Integer> getModuleIconList() {
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_inbox_black_24dp);
        mListIconItem.add(1, R.drawable.ic_star_black_24dp);
        mListIconItem.add(2, R.drawable.ic_settings_black_24dp);
        return mListIconItem;
    }

    @Override
    public SparseIntArray getModuleSparseCounter() {
        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
        mSparseCounterItem.put(0, 7);
        mSparseCounterItem.put(6, 250);
        return mSparseCounterItem;
    }
}
