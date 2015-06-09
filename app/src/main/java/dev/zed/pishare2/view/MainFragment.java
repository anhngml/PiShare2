package dev.zed.pishare2.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dev.zed.pishare2.R;
import dev.zed.pishare2.entity.BaseItem;
import dev.zed.pishare2.presenter.ContentPresenter;
import dev.zed.pishare2.presenter.interfaces.IContentPresenter;
import dev.zed.pishare2.view.adapter.BaseListAdapter;
import dev.zed.pishare2.view.adapter.FeedListAdapter;
import dev.zed.pishare2.view.adapter.UserListAdapter;
import dev.zed.pishare2.view.interfaces.IMainFragView;

public class MainFragment extends Fragment implements IMainFragView {

    public static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    private ListView listView;
    private BaseListAdapter listAdapter;
    private List<BaseItem> Items;
    private boolean mSearchCheck;
    Activity activity;
    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck) {
                // implement your search here
            }
            return false;
        }
    };
    private int type = -1;
    private IContentPresenter contentPresenter;

    public MainFragment newInstance(String text, int _type) {
        MainFragment mFragment = new MainFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        mFragment.type = _type;
        mFragment.contentPresenter = new ContentPresenter(mFragment);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        activity = this.getActivity();
        listView = (ListView) rootView.findViewById(R.id.list);
//        listView.setAdapter(getListAdapter(activity, Items));
        rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

        menu.findItem(R.id.menu_add).setVisible(true);
        menu.findItem(R.id.menu_search).setVisible(true);

        mSearchCheck = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {

            case R.id.menu_add:

                break;

            case R.id.menu_search:
                mSearchCheck = true;

                break;
        }
        return true;
    }

    @Override
    public int getType() {
        return type;
    }

    BaseListAdapter getListAdapter(Activity activity, List<BaseItem> items) {
        if (type == 0)
            listAdapter = new FeedListAdapter(activity, items);
        else if (type == 1)
            listAdapter = new UserListAdapter(activity, items);
        else
            listAdapter = null;
        return listAdapter;
    }

    @Override
    public void setItems(ArrayList<BaseItem> items) {
        Items = items;
        if(listView != null && activity != null)
        listView.setAdapter(getListAdapter(activity, Items));
//        if(listAdapter != null)
//        listAdapter.notifyDataSetChanged();
    }
}
