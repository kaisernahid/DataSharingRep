package com.CS223.DataSharing;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import com.DataSharing.R;

public class MainGUI extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        final int TABHEIGTH = 40;
        int tabIndex = 0;
        

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, RecommendsActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("recommends").setIndicator("Recommends").setContent(intent);
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(tabIndex++).getLayoutParams().height = TABHEIGTH;

        // Do the same for the other tabs
        intent = new Intent().setClass(this, CategoriesActivity.class);
        spec = tabHost.newTabSpec("categories").setIndicator("Categories").setContent(intent);
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(tabIndex++).getLayoutParams().height = TABHEIGTH;

        intent = new Intent().setClass(this, MyAppsActivity.class);
        spec = tabHost.newTabSpec("myapps").setIndicator("My Apps").setContent(intent);
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(tabIndex++).getLayoutParams().height = TABHEIGTH;

        tabHost.setCurrentTab(0);
    }
}