package com.gazematic.loofinder;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends FragmentActivity implements
        FragmentToActivity  {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    //private PullDataTask getPhotosTask;
    public String currentLanguage = "en";
    private GoogleMap mMap;
    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // Language settings
    Typeface hindiFont, englishFont, tamilFont;
    public static SharedPreferences sharedPreferences = null;
    public static SharedPreferences.Editor editor = null;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    //Gson gson = new Gson();
    private ArrayList<NavDrawerItem> navDrawerItems;
    private DrawerListAdapter adapter;
    private JSONArray jsonarray = null;
    private JSONObject jsonobject = null;
    Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





    initSharedPreferences();
    setUpNavDrawerItems();

    // initialize font typeface
    hindiFont = Typeface.createFromAsset(getAssets(), "fonts/mangal.ttf");
    //callTasks();
    if (savedInstanceState == null) {
        // on first time display view for first nav item
        displayView(1);
        changeLang(0);
    }


//        Button fab = (Button) findViewById(R.id.find);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Finding public loo", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//
//
//                Fragment fragment = new NavigateFragment();
//
//                if (fragment != null) {
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.frame_container, fragment).commit();
//
//                    // update selected item and title, then close the drawer
//                    //mDrawerList.setItemChecked(position, true);
//                    //mDrawerList.setSelection(position);
//                    // setTitle(navMenuTitles[position - 1]);
//                    mDrawerLayout.closeDrawer(mDrawerList);
//                } else {
//                    // error in creating fragment
//                    Log.e("MainActivity", "Error in creating fragment");
//                }
//            }
//        });



}


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//
//        //28.6165465,77.1921426 - Rashtrapati Bhavan
//        //28.6077542,77.1874975 - Sardar patel Marg
//        //28.614875, 77.190106  - Mother Terasa Crescent
//        //28.623589, 77.200853  - Talkatora road
//
//        // Add a marker in Sydney and move the camera
//        LatLng president_estate = new LatLng(28.6177897,77.1951896);
//        LatLng rashtrapati_bhavan = new LatLng(28.6165465,77.1921426);
//        LatLng sardar_patel_marg = new LatLng(28.6077542,77.1874975);
//        LatLng mother_terasa_crescent = new LatLng(28.614875, 77.190106);
//        LatLng talkatora_road = new LatLng(28.623589, 77.200853);
//
//
//
//
//
//        mMap.addMarker(new MarkerOptions().position(president_estate).title("Rashtrapati Bhavan"));
//        mMap.addMarker(new MarkerOptions().position(rashtrapati_bhavan).title("Rashtrapati Bhavan"));
//        mMap.addMarker(new MarkerOptions().position(sardar_patel_marg).title("Rashtrapati Bhavan"));
//        mMap.addMarker(new MarkerOptions().position(mother_terasa_crescent).title("Rashtrapati Bhavan"));
//        mMap.addMarker(new MarkerOptions().position(talkatora_road).title("Rashtrapati Bhavan"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(president_estate));
//    }


    private void setUpNavDrawerItems() {
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.home), navMenuIcons.getResourceId(0, -1)));

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.introduction), navMenuIcons.getResourceId(1, -1)));

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.facts), navMenuIcons.getResourceId(2, -1)));

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.quiz), navMenuIcons.getResourceId(3, -1)));

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.videos), navMenuIcons.getResourceId(4, -1)));

        navDrawerItems.add(new NavDrawerItem(getResources().getString(
                R.string.audios), navMenuIcons.getResourceId(5, -1)));

//        navDrawerItems.add(new NavDrawerItem(getResources().getString(
//                R.string.prevention), navMenuIcons.getResourceId(6, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new DrawerListAdapter(getApplicationContext(),
                navDrawerItems, "hi");
        setUpHeaderView();
        mDrawerList.setAdapter(adapter);

        getActionBar().setTitle("Loo-Fi");

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, // nav menu toggle icon
                R.string.app_name, // nav drawer open - description for //
                // accessibility
                R.string.app_name // nav drawer close - description for
                // accessibility
        ) {
            public void onDrawerClosed(View view) {
                //setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
               // setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        setTitle(getResources().getString(
//                R.string.home));
    }

    private void setUpHeaderView() {
        try {
            mDrawerList.removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View header = inflater
                .inflate(R.layout.drawer_list_header, null, false);
        CircleImageView iconImage = (CircleImageView) header
                .findViewById(R.id.userIcon);
        iconImage.setImageDrawable(getResources().getDrawable(
                R.drawable.googleglass));
        SharedPreferencesController sharedPreferencesController = SharedPreferencesController
                .getSharedPreferencesController(this);
        TextView userName = (TextView) header.findViewById(R.id.userName);
        userName.setText("Karthikeyan NG");
        mDrawerList.addHeaderView(header);
    }

    @Override
    public void callFragment(int fragmentId) {
        switch (fragmentId) {
            case 0:
                displayView(1);
                break;
            case 1:
                displayView(6);
                break;
            case 2:
                displayView(5);
                break;
            case 3:
                displayView(4);
                break;
            case 4:
                displayView(3);
                break;
            case 5:
                displayView(2);
                break;
        }
    }

/**
 * Slide menu item click listener
 * */
private class SlideMenuClickListener implements
        ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // display view for selected nav drawer item
        displayView(position);
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teach_aid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_english:
                changeLang(0);
                return true;
            case R.id.action_hindi:
                changeLang(1);
                return true;
            case R.id.action_tamil:
                changeLang(2);
                return true;
            case R.id.action_gujarati:
                changeLang(3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeLang(int lang) {
        if (lang == 0) {
            /**
             * "en" is the localization code for our English language.
             */
            currentLanguage = "en";
            displayView(1);
            Locale locale = new Locale(currentLanguage);
            Locale.setDefault(locale);
            config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            adapter.notifyDataSetChanged();

            // setting the nav drawer list adapter
            adapter = new DrawerListAdapter(getApplicationContext(),
                    navDrawerItems, currentLanguage);
            // setUpHeaderView();
            mDrawerList.setAdapter(adapter);

            invalidateOptionsMenu();
            //setTitle(mTitle);
        }

        if (lang == 1) {

            /**
             * "hi" is the localization code for our Hindi language.
             */
            currentLanguage = "hi";
            displayView(1);
            Locale locale = new Locale(currentLanguage);
            Locale.setDefault(locale);

            config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            adapter.notifyDataSetChanged();

            // setting the nav drawer list adapter
            adapter = new DrawerListAdapter(getApplicationContext(),
                    navDrawerItems, currentLanguage);
            // setUpHeaderView();
            mDrawerList.setAdapter(adapter);
            invalidateOptionsMenu();
            //setTitle(mTitle);
        }

        if (lang == 2) {

            /**
             * "ta" is the localization code for our Tamil language.
             */
            currentLanguage = "ta";
            displayView(1);
            Locale locale = new Locale(currentLanguage);
            Locale.setDefault(locale);

            config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            adapter.notifyDataSetChanged();

            // setting the nav drawer list adapter
            adapter = new DrawerListAdapter(getApplicationContext(),
                    navDrawerItems, currentLanguage);
            // setUpHeaderView();
            mDrawerList.setAdapter(adapter);
            invalidateOptionsMenu();
            //setTitle(mTitle);
        }


//        if (lang == 3) {
//
//            /**
//             * "gj" is the localization code for our Gujarati language.
//             */
//            currentLanguage = "gj";
//            displayView(1);
//            Locale locale = new Locale(currentLanguage);
//            Locale.setDefault(locale);
//
//            config = new Configuration();
//            config.locale = locale;
//            getBaseContext().getResources().updateConfiguration(config,
//                    getBaseContext().getResources().getDisplayMetrics());
//
//            adapter.notifyDataSetChanged();
//
//            // setting the nav drawer list adapter
//            adapter = new DrawerListAdapter(getApplicationContext(),
//                    navDrawerItems, currentLanguage);
//            // setUpHeaderView();
//            mDrawerList.setAdapter(adapter);
//            invalidateOptionsMenu();
//            setTitle(mTitle);
//        }
    }

    // Method to setup custom action bar
    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actionbar_common, null);
        TextView viewName = (TextView) v.findViewById(R.id.actionbar_text);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Thin.ttf");
        viewName.setTypeface(tf);
        viewName.setText(getString(R.string.app_name));
        actionBar.setCustomView(v);
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    public String getLanguage() {
        return currentLanguage;
    }


    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"intrepidkarthi@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Loo-Fi");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello,");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.d("Finished sending email", "Cool");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HomeActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new MyMapFragment();
                //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


                //mapFragment.getMapAsync(this);
                //setTitle(getResources().getString(R.string.home));
                break;
            case 2:
                fragment = new NavigateFragment();

                //setTitle(getResources().getString(R.string.introduction));
                break;
            case 3:
                fragment = new IssuesFragment();
               // setTitle(getResources().getString(R.string.facts));
                break;
            case 4:
                fragment = new UploadFragment();
                //setTitle(getResources().getString(R.string.quiz));
                break;
            case 5:
                shareContent();
               // setTitle(getResources().getString(R.string.videos));
                break;
            case 6:
                sendEmail();
               // setTitle(getResources().getString(R.string.audios));
                break;
            case 7:
                sendEmail();
              //  setTitle(getResources().getString(R.string.prevention));
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            // setTitle(navMenuTitles[position - 1]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    protected void shareContent()
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Loo-fi invites every Indian citizen to help us building a better India.");
        startActivity(Intent.createChooser(sharingIntent,"Share using"));
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = mDrawerTitle = title;

        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionBarTextView = (TextView) findViewById(titleId);
        if (currentLanguage.equals("ta")) {
            Typeface tf = Typeface.createFromAsset(getAssets(),
                    "fonts/Bamini.ttf");
            actionBarTextView.setTypeface(tf);
            // actionBarTextView.setText(mTitle);
        }
            if (currentLanguage.equals("hi")) {
                Typeface tf = Typeface.createFromAsset(getAssets(),
                        "fonts/mangal.ttf");
                actionBarTextView.setTypeface(tf);
                // actionBarTextView.setText(mTitle);
            }


//        if (currentLanguage.equals("gj")) {
//            Typeface tf = Typeface.createFromAsset(getAssets(),
//                    "fonts/Lohit-Gujarati.ttf");
//            actionBarTextView.setTypeface(tf);
//            // actionBarTextView.setText(mTitle);
//        }
        getActionBar().setTitle(mTitle);

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    void initSharedPreferences() {
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
    }







}
