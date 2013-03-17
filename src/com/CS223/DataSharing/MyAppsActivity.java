package com.CS223.DataSharing;

import java.util.ArrayList;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
//import android.content.pm.IPackageDeleteObserver;
import com.DataSharing.R;

public class MyAppsActivity extends ListActivity {

	//new variables
	  private final String TAG="UninstallAppProgress";
	  private boolean localLOGV = false;
	  private ApplicationInfo mAppInfo;
	  private TextView mStatusTextView;
	  private Button mOkButton;
	  private ProgressBar mProgressBar;
	  private View mOkPanel;
	  private volatile int mResultCode = -1;
	  private final int UNINSTALL_COMPLETE = 1;
	  public final static int SUCCEEDED=1;
	  public final static int FAILED=0;
	//new variables
	
	       class PackageDeleteObserver extends IPackageDeleteObserver.Stub {
		           public void packageDeleted(boolean succeeded) {
		               //Message msg = mHandler.obtainMessage(UNINSTALL_COMPLETE);
		               //msg.arg1 = succeeded?SUCCEEDED:FAILED;
		               //mHandler.sendMessage(msg);
		           }
	       }
		       
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        ArrayList<String> results = new ArrayList<String>();
        
        ListView lView = getListView();    	
        //lView.setBackgroundColor(R.color.screen_background);
        lView.setBackgroundResource(R.color.screen_background);
        lView.setTextFilterEnabled(true);
        
        PackageManager pm = this.getPackageManager();
        
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
        for(ResolveInfo rInfo : list){
        	results.add(rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
        	Log.w("Installed Applications", rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
        }
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
        //setListAdapter(new ArrayAdapter<String>(this, R.layout.recommends, results));
        
        lView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		// When clicked, show a toast with the TextView text
        		Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
        				Toast.LENGTH_SHORT).show();
        		
        		// un-install the app
        		//Uri packageURI = Uri.parse("package:com.android.HelloLocation");
        		
        		Uri packageURI = Uri.parse("package:com.example.hellolocation.HelloLocation ");
        		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        		startActivity(uninstallIntent);
        		/*
        		           requestWindowFeature(Window.FEATURE_NO_TITLE);
        		           //setContentView(R.layout.uninstall_progress);
        		           // Initialize views
        		          //PackageUtil.initSnippetForInstalledApp(this, mAppInfo, R.id.app_snippet);
        		          //mStatusTextView = (TextView)findViewById(R.id.center_text);
        		          mStatusTextView.setText("R.string.uninstalling");
        		          //mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        		          mProgressBar.setIndeterminate(true);
        		          // Hide button till progress is being displayed
        		          //mOkPanel = (View)findViewById(R.id.ok_panel);
        		          //mOkButton = (Button)findViewById(R.id.ok_button);
        		          //mOkButton.setOnClickListener(this);
        		          mOkPanel.setVisibility(View.INVISIBLE);
        		          PackageDeleteObserver observer = new PackageDeleteObserver();
        		          //getPackageManager().deletePackage(mAppInfo.packageName, null, 0);
        		*/
        		
        	}
        });

    }
}
