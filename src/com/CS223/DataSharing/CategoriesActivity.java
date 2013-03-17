package com.CS223.DataSharing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.CS223.DataSharing.RecommendsActivity.EfficientAdapter;
import com.CS223.DataSharing.RecommendsActivity.EfficientAdapter.ViewHolder;
import com.DataSharing.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoriesActivity extends ListActivity {
	private EfficientAdapter adap;
	private static ArrayList<String> categoryNames = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
    	/*
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Categories tab");
        setContentView(textview);
        */
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//new
		setContentView(R.layout.code_mobile);
		adap = new EfficientAdapter(this);
		setListAdapter(adap);

		//ArrayList<String> results = new ArrayList<String>();

		ListView lView = getListView();    	
		lView.setTextFilterEnabled(true);

		String result = postData();
		try{
			JSONArray jsons = new JSONArray(result);
			for (int i = 0; i < jsons.length(); i++) {
				JSONObject json = jsons.getJSONObject(i);
				categoryNames.add(json.getString("categoryName"));
			}
		}
		catch (JSONException e) {
			Log.e("JSON", "There was an error parsing the JSON", e);
		}        
    }
	//http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient
	public String postData() {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://173.255.215.27:8082/");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("q", "recommends"));
			//nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = RestClient.convertStreamToString(instream);

				instream.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			Log.e("REST", "There was a protocol based error", e);
		} catch (IOException e) {
			Log.e("REST", "There was an IO Stream related error", e);
		}
		return null;
	}     
	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private Context context;

		public EfficientAdapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
		}
		
//*/
		/**
		 * Make a view to hold each row.
		 *
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
///*
		public View getView(final int position, View convertView, ViewGroup parent) {
			// A ViewHolder keeps references to children views to avoid
			// unneccessary calls
			// to findViewById() on each row.
			ViewHolder holder;

			// When convertView is not null, we can reuse it directly, there is
			// no need
			// to reinflate it. We only inflate a new View when the convertView
			// supplied
			// by ListView is null.
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.category_content, null);

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();
				holder.categoryLine = (TextView) convertView.findViewById(R.id.categoryLine);

				convertView.setOnClickListener(new OnClickListener() {
					private int pos = position;

					@Override
					public void onClick(View v) {
						Toast.makeText(context, "Click-" + String.valueOf(pos), Toast.LENGTH_SHORT).show();   
					}
				});
				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			// Bind the data efficiently with the holder.
			holder.categoryLine.setText(""+categoryNames.get(position));

			return convertView;
		}

		static class ViewHolder {
			//TextView textLine;
			TextView categoryLine;
			//ImageView iconLine;
			//Button buttonLine;
		}

		@Override
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			//return data.length;
			return categoryNames.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			//return data[position];
			return categoryNames.get(position);
		}
	}//*/		
}
