package com.restaurant.arrifqiaziz.activity;

import androidx.annotation.ColorRes;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.adapter.bottomnav.BottomBarAdapter;
import com.restaurant.arrifqiaziz.adapter.bottomnav.NoSwipePager;
import com.restaurant.arrifqiaziz.fragment.HomeFragment;
import com.restaurant.arrifqiaziz.fragment.ProfilFragment;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.viewpager)
    NoSwipePager viewPager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    int pos = 0;

    private BottomBarAdapter pagerAdapter;
    private boolean notificationVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setupViewPager();
        setupBottomNavBehaviors();
        setupBottomNavStyle();

        addBottomNavigationItems();
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                pos = position;

                if (!wasSelected)
                    viewPager.setCurrentItem(position);

                // remove notification badge
                int lastItemPos = bottomNavigation.getItemsCount() - 1;
                if (notificationVisible && position == lastItemPos)
                    bottomNavigation.setNotification(new AHNotification(), lastItemPos);

                return true;
            }
        });
    }

    private void setupViewPager() {
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(new HomeFragment());
        pagerAdapter.addFragments(new ProfilFragment());

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
    }


    public void setupBottomNavBehaviors() {
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setTranslucentNavigationEnabled(false);
    }

    private void setupBottomNavStyle() {
        bottomNavigation.setColored(false);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.bottomtab));
        bottomNavigation.setAccentColor(fetchColor(R.color.colorPrimary));
        bottomNavigation.setInactiveColor(fetchColor(R.color.bottomtab_item_resting));
        bottomNavigation.setColoredModeColors(getResources().getColor(R.color.colorPrimary),
                fetchColor(R.color.bottomtab_item_resting));
        //bottomNavigation.setColored(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    }

    private void addBottomNavigationItems() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Beranda", R.drawable.ic_menu_home, R.color.bottomtab);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Profile", R.drawable.ic_menu_akun, R.color.bottomtab);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}
