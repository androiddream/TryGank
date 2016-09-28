package com.gypsophila.trygank.main.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gypsophila.trygank.R;
import com.gypsophila.trygank.about.view.AboutFragment;
import com.gypsophila.trygank.base.AppBaseActivity;
import com.gypsophila.trygank.main.presenter.IMainPresenter;
import com.gypsophila.trygank.main.presenter.MainPresenterImpl;
import com.gypsophila.trygank.news.view.NewsFragment;

/**
 * Created by Gypsophila on 2016/8/16.
 */
public class MainActivity extends AppBaseActivity implements IMainView {

    private Toolbar mToolbar;
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private IMainPresenter mMainPresenter;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this,R.color.colorPrimary);
        mContext = this;
        initToolbar();
        initView();
        mMainPresenter = new MainPresenterImpl(this);
        switchToNews();
    }

    private void setTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout) {
    }

    private void initView() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.base_toolbar_menu); //设置右上角的填充菜单
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //启用导航菜单
//        mToolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mMainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_search) {
                    Toast.makeText(mContext, R.string.menu_search, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.action_notification) {
                    Toast.makeText(mContext, R.string.menu_notifications, Toast.LENGTH_SHORT).show();

                } else if (id == R.id.action_item1) {
                    Toast.makeText(mContext, R.string.item_01, Toast.LENGTH_SHORT).show();

                } else if (id == R.id.action_item2) {
                    Toast.makeText(mContext, R.string.item_02, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public void switchToNews() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_content_container, NewsFragment.newInstance(), NewsFragment.TAG)
                .commit();
    }

    @Override
    public void switchToAbout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_content_container, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }
}
