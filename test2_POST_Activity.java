package com.example.test_json_volley;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {
	
	private String TAG = "TESTOM";
	private RequestQueue mRequestQueue;
	private TextView tvdebug;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvdebug = (TextView) findViewById(R.id.textview1);
		

		mRequestQueue =  Volley.newRequestQueue(this);
		String url = "http://www.disruptive-wolfpack.de/android/testoliver.php";
	 
		
		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST,url,null,new Response.Listener<JSONObject>() {
		            @Override
		            public void onResponse(JSONObject response) {
			                Log.i(TAG,response.toString());
			                
			                tvdebug.setText(response.toString());
			                
			                parseJSON(response);
	            			}
		        },new Response.ErrorListener() {
		           @Override
			            public void onErrorResponse(VolleyError error) {
			                Log.i(TAG,error.getMessage());
		            }
	        }){
			
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("tag", "TEST_Tag_Oliver");
				params.put("str", "TEST_str_Pass");
				return params;
			}
		};
		        mRequestQueue.add(jr);

	}

    private void parseJSON(JSONObject json){
        try{
            
        	String name = json.optString("name");
        	String nachname = json.optString("nachname");
        	String info = json.optString("info");

            Log.d(TAG,name);
            Log.d(TAG,nachname);
            Log.d(TAG,info);
        
        }
 	        catch(Exception e){
             e.printStackTrace();
         }
  
  
     }	

	
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
