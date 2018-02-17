/*
 * Copyright (C) 2018 Eric Afenyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.eric.quickheadline;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.eric.quickheadline.jobservice.ArticleSyncTask;
import com.example.eric.quickheadline.jobservice.WeatherSyncTask;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //    @BindView(R.id.tv_timezone)
//    TextView tvTimezone;
//    @BindView(R.id.tv_summary)
//    TextView tvSummary;
//    @BindView(R.id.ib_location)
//    ImageButton ibLocation;
//    @BindView(R.id.iv_icon)
//    ImageView ivIcon;
//    @BindView(R.id.tv_temperature)
//    TextView tvTemperature;
//    @BindView(R.id.tv_apparent_temperature)
//    TextView tvRealFeel;
//    @BindView(R.id.tv_humidity)
//    TextView tvHumidity;
//    @BindView(R.id.tv_uv_index)
//    TextView tvUvIndex;
//    @BindView(R.id.tv_visibility)
//    TextView tvVisibility;
//    @BindView(R.id.tv_wind_speed)
//    TextView tvWindSpeed;

    @BindView(R.id.navigation_view)
    BottomNavigationViewEx navigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//         mToolbar = findViewById(R.id.toolbar);
//
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setTitle("Latest");
//            mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu));
//        }

        WeatherSyncTask.execute(getApplication());
        ArticleSyncTask.execute(getApplication());

        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setOnClickListener(null);
        configureNavigationView();
        navigationView.setCurrentItem(0);


//        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
//                navigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

    }


    private void configureNavigationView(){
        navigationView.enableAnimation(true);
        navigationView.enableShiftingMode(false);
        navigationView.enableItemShiftingMode(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
//        int position = navigationView.getMenuItemPosition(item);
//        int count =navigationView.getItemCount();
//        Log.v("count",String.valueOf(count));
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = HomeFragment.newInstance();
                loadFragment(fragment);


                return true;
            case R.id.nav_Discover:

                fragment = CategoryFragment.newInstance();
                loadFragment(fragment);
//                Log.v("position",String.valueOf(position));

//                navigationView.setIconTintList(position,ContextCompat.getColorStateList(this,R.color.colorPrimary));

                return true;
            case R.id.nav_bookmarks:

                fragment = BookmarkFragment.newInstance();
                loadFragment(fragment);
//                Log.v("position",String.valueOf(position));

                return true;
            case R.id.nav_settings:
                fragment = SettingFragment.newInstance();
                loadFragment(fragment);
//                Log.v("position",String.valueOf(position));
                return true;

        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setChecked(){
        boolean checked = true;
        if (checked){

            navigationView.setSelectedItemId(0);
        }

    }




}
