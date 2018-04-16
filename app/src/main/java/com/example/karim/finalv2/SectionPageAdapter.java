package com.example.karim.finalv2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karim on 11/10/2017.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mfragmentList=new ArrayList<>();
    private final List<String> mfragmentListtitle=new ArrayList<>();


     public void addFragment(Fragment fragment, String s){
         mfragmentList.add(fragment);
         mfragmentListtitle.add(s);
     }
    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentListtitle.get(position);
    }
}
