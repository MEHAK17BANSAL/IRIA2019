package com.brandappz.alpcord.events.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.utils.Utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.brandappz.alpcord.events.activities.Config.CONFIRM_URL;

/**
 * Created by perveen akhter on 24-03-2018.
 */

public class FormActivity extends AppCompatActivity {
    EditText name, lastname, pass;
    ProgressDialog nProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.formac);
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        pass = (EditText) findViewById(R.id.pass);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(FormActivity.this, MainActivity.class));

                if (!name.getText().toString().trim().equalsIgnoreCase("")) {
                  //  if (!/*lastname.getText().toString().trim().equalsIgnoreCase("")*/true) {
                        if (!pass.getText().toString().trim().equalsIgnoreCase("")) {


                            if (Utils.isOnline(FormActivity.this)) {
                                Log.e("sss", CONFIRM_URL + "mobile=" + name.getText().toString() + " " + lastname.getText().toString() + "&otp=" + pass.getText().toString() + "&brand_code=QDVTD");
                                new Register().execute("");
                            } else {
                                Toast.makeText(FormActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(FormActivity.this, "Please enter passcode", Toast.LENGTH_SHORT).show();
                        }

                    /*} else {
                        Toast.makeText(FormActivity.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                    }*/

                } else {
                    Toast.makeText(FormActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class Register extends AsyncTask<String, String, String> {
        public HttpURLConnection connection = null;

        protected void onPreExecute() {
            super.onPreExecute();

            nProgressDialog = new ProgressDialog(FormActivity.this);
            nProgressDialog.setMessage("Loading..");
            nProgressDialog.setCancelable(false);
            nProgressDialog.show();

            //showDialog(DIALOG_DOWNLOAD_PROGRESS);
            //filedele();

        }

        protected String doInBackground(String... params) {

            BufferedReader reader = null;
            try {

                URL url = new URL(CONFIRM_URL + "mobile=" + name.getText().toString()  + "&otp=" + pass.getText().toString() + "&brand_code=QDVTD");
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                InputStream stream = connection.getInputStream();

                //  Log.i("result", "value=================+++++++++++++++" + stream);
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @SuppressLint("LogConditional")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {

                nProgressDialog.dismiss();
                if (connection.getResponseCode() == 200) {

                    JSONObject jsonObject = new JSONObject(result);
                    Log.i("result", "jsonobject=======" + jsonObject);
                    if (jsonObject.getBoolean("result")) {
                        JSONObject ob = jsonObject.optJSONObject("data");

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("DashBoard", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("userid", "true");
                        editor.commit();
                        editor.putString("userid", "true");
                        editor.commit();
                        editor.putString("login", "true");
                        editor.commit();
                        editor.putString("user_id", ob.optString("user_id"));
                        editor.commit();
                        editor.putString("userId", ob.optString("user_id"));
                        editor.commit();
                        editor.putString("name", ob.optString("name"));
                        editor.commit();
                        editor.putString("email", ob.optString("email"));
                        editor.commit();
                        editor.putString("mobile", ob.optString("mobile"));
                        editor.commit();

                        startActivity(new Intent(FormActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(FormActivity.this, "User is not registered...!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
