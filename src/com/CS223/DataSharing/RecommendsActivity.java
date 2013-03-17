package com.CS223.DataSharing;

import java.io.ByteArrayInputStream;
import java.io.File;
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

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import com.DataSharing.R;

public class RecommendsActivity extends ListActivity {

	private EfficientAdapter adap;
	private static String[] data = new String[] { "0", "1", "2", "3", "4" };
	private static ArrayList<String> results = new ArrayList<String>();
	private static ArrayList<String> categoryNames = new ArrayList<String>();
	private static ArrayList<String> descriptions = new ArrayList<String>();
	private static ArrayList<String> base64Strings = new ArrayList<String>();
	//http://www.androidsnippets.com/retrieve-json-from-a-rest-web-service
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//new
		setContentView(R.layout.code_mobile);
		
		/*
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
				results.add(json.getString("appName"));
				categoryNames.add(json.getString("categoryName"));
				descriptions.add(json.getString("description"));
				base64Strings.add(json.getString("icon"));
			}
			////setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
		}
		catch (JSONException e) {
			Log.e("JSON", "There was an error parsing the JSON", e);
		}
		*/
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
///*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(this, "Click-" + String.valueOf(position) + descriptions.get(position), Toast.LENGTH_SHORT).show();
	}

	public static class EfficientAdapter extends BaseAdapter implements Filterable {
		private LayoutInflater mInflater;
		private Bitmap mIcon1;
		private Context context;

		public EfficientAdapter(Context context) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			mInflater = LayoutInflater.from(context);
			this.context = context;
		}
		
		public Bitmap Base64ToImage(String base64String)
		{
			byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);			
			 
			return decodedByte;
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
				convertView = mInflater.inflate(R.layout.adaptor_content, null);

				// Creates a ViewHolder and store references to the two children
				// views
				// we want to bind data to.
				holder = new ViewHolder();
				holder.textLine = (TextView) convertView.findViewById(R.id.textLine);
				holder.categoryLine = (TextView) convertView.findViewById(R.id.categoryLine);
				holder.iconLine = (ImageView) convertView.findViewById(R.id.iconLine);
				//holder.buttonLine = (Button) convertView.findViewById(R.id.buttonLine);


				convertView.setOnClickListener(new OnClickListener() {
					private int pos = position;

					@Override
					public void onClick(View v) {
						Toast.makeText(context, "Click-" + String.valueOf(pos), Toast.LENGTH_SHORT).show();   
					}
				});
/*
				holder.buttonLine.setOnClickListener(new OnClickListener() {
					private int pos = position;

					@Override
					public void onClick(View v) {
						Toast.makeText(context, "Delete-" + String.valueOf(pos), Toast.LENGTH_SHORT).show();

					}
				});
*/


				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			// Get flag name and id
			String filename = "flag_" + String.valueOf(position);
			int id = context.getResources().getIdentifier(filename, "drawable", context.getString(R.string.package_str));

			// Icons bound to the rows.
			if (id != 0x0) {
				mIcon1 = BitmapFactory.decodeResource(context.getResources(), id);
			}

			mIcon1 = Base64ToImage(base64Strings.get(position)); 
				
			// Bind the data efficiently with the holder.
			holder.iconLine.setImageBitmap(mIcon1);
			//holder.textLine.setText("flag " + String.valueOf(position));
			//holder.textLine.setText("flag dfgh");
			holder.textLine.setText(""+results.get(position));
			holder.categoryLine.setText(""+categoryNames.get(position));

			return convertView;
		}

		static class ViewHolder {
			TextView textLine;
			TextView categoryLine;
			ImageView iconLine;
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
			return results.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			//return data[position];
			return results.get(position);
		}
	}//*/	
}