package com.example.goldmining.Utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Tools {
    private static   ArrayAdapter<String> arrayAdapter;
    private static ArrayList<String> arrayList = new ArrayList<>();
    private static RequestQueue requestQueue  ;

    public static String getAutoId(String tableName , String columnName){
        String Id = "";
        String[] field = new String[2];
        field[0] = "tableName";
        field[1] = "column";
        String[] data = new String[2];
        data[0] = tableName;
        data[1] = columnName;
        PutData putData = new PutData("https://egicta.com/Mill_Client/auto_id.php", "POST", field, data);
        if(putData.startPut()){
            if(putData.onComplete()){
                Id = putData.getResult();
            }
        }else {
            Id = "0";
        }
        return Id ;
    }

    public static void fillSpinner( Context context , String url , Activity activity ,Spinner spinner){
        requestQueue = Volley.newRequestQueue(context);
        arrayList = new ArrayList<>();
        String Url = "https://egicta.com/"+url+".php";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("names");
                    //Toast.makeText(context, jsonArray.length()+" ", Toast.LENGTH_SHORT).show();
                    for(int i = 0 ; i < jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("name");
                        arrayList.add(name);
                        arrayAdapter = new ArrayAdapter<>(activity.getApplication(), android.R.layout.simple_spinner_item,arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(arrayAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(objectRequest);
    }
    public static String[] oneColumn(Context context ,String tableName , String columnName ){
//        requestQueue = Volley.newRequestQueue(context);
        ArrayList arrayList = new ArrayList<>();
        String [] datas = new String[0];
        String[] field = new String[2];
        field[0] = "tableName";
        field[1] = "columnName";
        String[] data = new String[2];
        data[0] = tableName;
        data[1] = columnName;
        String url = "http://egicta.com/Mill_Client/selectOneColumn.php";//?columnName="+ColumnName+"&tableName="+tableName;
        PutData putData = new PutData(url,"POST",field,data);
        if(putData.startPut()){
            if(putData.onComplete()){
                try {
                    JSONArray jArr = new JSONArray(putData.getResult());
                    Toast.makeText(context, putData.getResult(), Toast.LENGTH_SHORT).show();
                    datas = new String[jArr.length()];
                    for (int count = 0; count < jArr.length(); count++){
                        JSONObject jsonObject = jArr.getJSONObject(count);
                        String row = jsonObject.getString(columnName);
                        datas[count] = row;
                        arrayList.add(row);
                       Toast.makeText(context, row, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
       return datas;
    }




    /*
     public void parseJson(String json) throws JSONException {

            JSONArray jArr = new JSONArray(json);

            for (int count = 0; count < jArr.length(); count++) {
                JSONObject obj = jArr.getJSONObject(count);
                double latitude= obj.getDouble("latitude");
                double longitude= obj.getDouble("longitude");
                String placeName= obj.getString("placeName");
                String hint = obj.getString("hint");
            }
        }
     */


}
/**
 *
 *
 *
 *
 * **/