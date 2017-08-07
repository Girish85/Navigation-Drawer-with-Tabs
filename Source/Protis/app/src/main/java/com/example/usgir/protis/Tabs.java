package com.example.usgir.protis;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tabs extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        pager = (ViewPager)findViewById(R.id.pager);
        myadapter = new Myadapter(getSupportFragmentManager());
        pager.setAdapter(myadapter);
        tabLayout.setupWithViewPager(pager);
    }
    public class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Myfragment.getfragment(position+1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0 : return "Home";
                case 1 : return "Account";
                default: return "Share";
            }
        }
    }
    public static class Myfragment extends Fragment{

        public static Fragment getfragment(int position)
        {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("pos",position);
            myfragment.setArguments(args);
            return myfragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            int i = getArguments().getInt("pos");
            if (i==1)
            {
                View view = inflater.inflate(R.layout.fragment_home,container,false);
                return view;
            }
            else
            {
                View view = inflater.inflate(R.layout.fragment_share, container, false);
                return view;
            }
        }
    }
}
