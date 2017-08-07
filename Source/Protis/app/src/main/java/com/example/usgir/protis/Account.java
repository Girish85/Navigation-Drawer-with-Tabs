package com.example.usgir.protis;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Account extends Fragment {
    String s[];
    Myadapter myadapter;
    TabLayout tabLayout;
    ViewPager pager;
    Context context;
    public Account() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        s = new String[]{"Home","Share","Account","Default"};
        tabLayout = (TabLayout)view.findViewById(R.id.tablay);
        pager = (ViewPager)view.findViewById(R.id.pager1);
        context = getActivity();
        FragmentManager fma = ((FragmentActivity)context).getSupportFragmentManager();
        myadapter = new Myadapter(fma);
        pager.setAdapter(myadapter);
        tabLayout.setupWithViewPager(pager);
        return view;
    }
    public class Myadapter extends FragmentPagerAdapter
    {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return Myfragment.getFragment(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
                return s[position];
        }
    }
    public static class Myfragment extends android.support.v4.app.Fragment
    {
        public static android.support.v4.app.Fragment getFragment(int pos)
        {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("pos",pos);
            myfragment.setArguments(args);
            return myfragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            int i = getArguments().getInt("pos");
            if (i==0||i==2)
                return inflater.inflate(R.layout.fragment_home,container,false);
            else
                return inflater.inflate(R.layout.fragment_share,container,false);
        }
    }
}
