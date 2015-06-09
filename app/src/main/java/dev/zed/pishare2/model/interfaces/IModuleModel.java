package dev.zed.pishare2.model.interfaces;

import android.util.SparseIntArray;

import java.util.List;

/**
 * Created by Dr on 4/7/2015.
 */
public interface IModuleModel {
    public List<String> getModuleList();
    public List<Integer> getModuleIconList();
    public SparseIntArray getModuleSparseCounter();
}
