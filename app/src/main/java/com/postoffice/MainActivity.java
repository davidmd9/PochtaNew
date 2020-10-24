package com.postoffice;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.postoffice.base.BaseActivity;
import com.postoffice.base.BaseFragment;
import com.postoffice.fragments.MainFragment;
import com.postoffice.main.MainActivityContract;
import com.postoffice.main.MainActivityPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private MainActivityPresenter presenter = new MainActivityPresenter();

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        presenter.attach(this);
        FirebaseApp.initializeApp(this);
        pushFragment(new MainFragment(), false);
    }

    @Override
    public void pushFragment(BaseFragment fragment, boolean is_add) {
        hideKeyboard();
        fragment.attachNavigationPresenter(presenter);
        NavigationManager.replaceFragment(MainActivity.this, fragment, R.id.flContent, is_add);
    }
}