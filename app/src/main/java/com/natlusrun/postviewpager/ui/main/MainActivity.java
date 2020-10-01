package com.natlusrun.postviewpager.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.natlusrun.postviewpager.R;
import com.natlusrun.postviewpager.adapters.FragmentAdapter;
import com.natlusrun.postviewpager.ui.favourite.FavouriteFragment;
import com.natlusrun.postviewpager.ui.list.ListFragment;
import com.natlusrun.postviewpager.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fillFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentAdapter(fragmentList, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.list_menu) {
                    viewPager.setCurrentItem(0);
                }else if (item.getItemId() == R.id.fav_menu){
                    viewPager.setCurrentItem(1);
                }else if (item.getItemId() == R.id.profile_menu) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });

    }

    private void fillFragment() {
        fragmentList.add(new ListFragment());
        fragmentList.add(new FavouriteFragment());
        fragmentList.add(new ProfileFragment());
    }
}