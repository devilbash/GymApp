package mcs.gymapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

import mcs.gymapp.db.entita.AllenamentoI;
import mcs.gymapp.fragment.FragCorso;
import mcs.gymapp.utility.menu.NavigationDrawerFragment;
import mcs.gymapp.fragment.Frag;
import mcs.gymapp.fragment.FragNews;
import mcs.gymapp.fragment.FragScheda;
import mcs.gymapp.utility.SingletonParametersBridge;
import mcs.gymapp.utility.menu.Scheda;
import mcs.gymapp.utility.setting.SettingsManager;


public class Main extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    SingletonParametersBridge singleton;


    private static final int REQUEST_CODE_SETTINGS = 0;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private FragNews   newsF;
    private FragScheda schedaF;
    private List<Frag> frags;

    public Main(){
        frags   = new ArrayList<>();
        newsF   = new FragNews();

        frags.add(newsF);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        singleton = SingletonParametersBridge.getInstance();
        SettingsManager settings = (SettingsManager) singleton.getParameter("settings");

        //Connect connection = new Connect(newsF );
        settings.setSync(false);

    //    singleton.addParameter("connessione",connection);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                //.replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .replace(R.id.container, (Fragment) frags.get(position))
                .commit();
    }

    @Override
    public void setAllenamento(Scheda items) {
        Frag f;
        for( AllenamentoI a : items.getScheda().getListAI()){
            if (a.getTipo() == 2) {
                f = new FragCorso();
                f.setAllenamento(a);
                frags.add(f);
            }else if (a.getTipo() == 1) {
                f = new FragScheda();
                f.setAllenamento(a);
                frags.add(f);
            }
        }
    }



/*
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
        }
    }
*/
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SETTINGS);
        }

        return super.onOptionsItemSelected(item);
    }
}
