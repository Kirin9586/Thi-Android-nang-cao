package com.example.giuakyanroidnangcao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class LoginAsynTask extends AsyncTask<String,Boolean, JSONObject> {
    private Context context;
    private Iview mview;
    private Map<String,String>map;
    private ProgressDialog dialog;
    public LoginAsynTask(Context context,Iview mview,Map<String,String>map){
        this.context = context;
        this.mview = mview;
        this.map = map;
    }

    @Override
    protected void onPreExecute() {
        dialog =new ProgressDialog(context);
        dialog.setTitle("Loading, please wait...");
        dialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
            JSONObject jsonObject = new JSONObject();
            Iterator iterator  = map.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = map.get(key);
                jsonObject.put(key,value);
            }
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //------------//
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = bufferedReader.readLine()) != null){
                stringBuffer.append(result);
            }
            result  = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            publishProgress(true);
            return parentObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {
        if (values[0]== true){
            dialog.dismiss();
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null){
            try {
                int mResult = jsonObject.getInt("result");
                String mMessage = jsonObject.getString("response_message");
                if (mResult > 0){
                    mview.onLoginSuccess(mMessage);
                }
                else {
                    mview.onLoginFailer(mMessage);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
