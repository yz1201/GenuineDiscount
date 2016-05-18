package com.example.tyz1201.mytablayout.util;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyz1201.mytablayout.R;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tyz1201 on 2016/4/18.
 */
public class Injector {
    private FragmentActivity fragmentActivity;
    private TabLayout tabLayout;
    private TabModel[] tabModels = null;
    private FragmentManager fragmentManager;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    private Injector(FragmentActivity fragmentActivity, int tabLayoutId) {
        this.fragmentActivity = fragmentActivity;
        tabLayout = (TabLayout) fragmentActivity.findViewById(tabLayoutId);
        fragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    public static void inject(FragmentActivity fragmentActivity, int tabLayoutId, TabModel... tabModels) {
        Injector injector = new Injector(fragmentActivity, tabLayoutId);
        injector.tabModels = tabModels;
        injector.initTab();
        injector.initFragment();
    }

    public static TabModel newTabModel(String tabName, int tabImageId, Class fragmentClass) {
        return new TabModel(tabName, tabImageId, fragmentClass);
    }

    private void initTab() {
        for (TabModel tabModel : tabModels) {
            addTab(tabModel.getTabName(), tabModel.getTabImageId());
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(true);
                initFragment();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tab);
                textView.setSelected(true);
            }
        });
        tabLayout.getTabAt(0).select();
    }

    private void addTab(String tabName, int tabImageId) {
        LayoutInflater inflater = LayoutInflater.from(fragmentActivity);
        View view = inflater.inflate(R.layout.tab_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
        imageView.setImageResource(tabImageId);

        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(tabName);

        TabLayout.Tab tab = tabLayout.newTab();
        tab.setCustomView(view);
        tabLayout.addTab(tab);
    }

    private void initFragment() {
        int position = tabLayout.getSelectedTabPosition();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        showFragment(position, fragmentTransaction);
        fragmentTransaction.commit();
    }

    private void showFragment(int position, FragmentTransaction fragmentTransaction) {
        if (fragmentMap.get(position) == null) {
            Fragment fragment = getFragmentInstance(tabModels[position].getaClass());
            fragmentTransaction.add(R.id.fl_fragment, fragment);
            fragmentMap.put(position, fragment);
        } else {
            fragmentTransaction.show(fragmentMap.get(position));
        }
    }

    private Fragment getFragmentInstance(Class clas) {
        try {
            return (Fragment) clas.getMethod("newInstance").invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        for (Map.Entry<Integer, Fragment> entry : fragmentMap.entrySet()) {
            fragmentTransaction.hide(entry.getValue());

        }
    }

    public static class TabModel {
        private String tabName;
        private int tabImageId;
        private Class aClass;

        private TabModel(String tabName, int tabImageId, Class aClass) {
            this.tabName = tabName;
            this.tabImageId = tabImageId;
            this.aClass = aClass;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public int getTabImageId() {
            return tabImageId;
        }

        public void setTabImageId(int tabImageId) {
            this.tabImageId = tabImageId;
        }

        public Class getaClass() {
            return aClass;
        }

        public void setaClass(Class aClass) {
            this.aClass = aClass;
        }
    }


}
