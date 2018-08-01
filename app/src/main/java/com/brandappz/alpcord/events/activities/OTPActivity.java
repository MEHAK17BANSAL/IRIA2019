package com.brandappz.alpcord.events.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alimuzaffar.lib.widgets.AnimatedEditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.helper.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_SMS;


public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTENT_PHONENUMBER = "phonenumber";
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int TIMEOUT_MILLISEC = 1000;
    public EditText editTextConfirmOtp, editTextConfirmOtp1;
    public LinearLayout one, two;
    TextInputLayout loginInputLayoutEmail1;
    AnimatedEditText phoneNumber1;
    AppCompatButton buttonConfirm;
    private AnimatedEditText phoneNumber;
    private Handler handler;
    private Activity activity;
    private ProgressDialog nProgressDialog;
    private Runnable starter;
    private TextInputLayout loginInputLayoutEmail;
    private ViewPager viewPager;
    private Button buttonRegister;
    private PrefManager pref;
    private RequestQueue requestQueue;
    private String contact;
    private String code = "QDVTD";
    private String and = "&";
    private String mobile = "mobile";
    private String otp1 = "otp";
    private String brand = "brand_code";
    private String val = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
      /*  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
     bye battery khatam gn
     ok
     */


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_otp);


        //one = (LinearLayout)findViewById(R.id.one);
        //two = (LinearLayout)findViewById(R.id.two);
        //one.setVisibility(View.VISIBLE);
//        two.setVisibility(View.GONE);
        //loginInputLayoutEmail = (TextInputLayout) findViewById(R.id.login_input_layout_email);
        phoneNumber = (AnimatedEditText) findViewById(R.id.txt_pop_in);
        buttonRegister = (AppCompatButton) findViewById(R.id.smsVerificationButton);

       /*  loginInputLayoutEmail1= (TextInputLayout) findViewById(R.id.login_input_layout_email1);
         phoneNumber1 = (EditText) findViewById(R.id.phoneNumber1);
         buttonConfirm = (AppCompatButton) findViewById(R.id.smsVerificationButton1);
*/
        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(this);
       /* phoneNumber.setKeyListener(null);
        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    phoneNumber.callOnClick();
                }
            }
        });*/

        //tryAndPrefillPhoneNumber();
        pref = new PrefManager(this);
        //Adding a listener to button
        buttonRegister.setOnClickListener(this);

        if (pref.isWaitingForSms()) {
            viewPager.setCurrentItem(1);
            two.setVisibility(View.VISIBLE);
        }
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(OTPActivity.this);
        builder.setMessage("Are you sure close the App");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
                moveTaskToBack(true);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /*@Override
    public void onBackPressed() {
       // handler.removeCallbacks(starter);
        super.onBackPressed();
    }*/

    //This method would confirm the otp
    private void confirmOtp() throws JSONException {
        //two.setVisibility(View.VISIBLE);
        // one.setVisibility(View.GONE);

        //setContentView(R.layout.activity_o);
        setContentView(R.layout.activity_otp_new);

        loginInputLayoutEmail1 = (TextInputLayout) findViewById(R.id.login_input_layout_email1);
        phoneNumber1 = (AnimatedEditText) findViewById(R.id.phoneNumber1);
        buttonConfirm = (AppCompatButton) findViewById(R.id.smsVerificationButton1);

        //Creating a LayoutInflater object for the dialog box
        //LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        //View confirmDialog = li.inflate(R.layout.activity_otp, null);
        //setContentView(R.layout.activity_otp);
        //Initizliaing confirm button fo dialog box and edittext of dialog box
        //buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        //editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);


        //Creating an alertdialog builder
        // AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        // alert.setView(confirmDialog);

        //Creating an alert dialog
        //final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        // alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LogConditional")
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog


                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(OTPActivity.this, "Authenticating", "Please wait...", false, false);

                //Getting the user entered otp from edittext
                final String otp = phoneNumber1.getText().toString().trim();
                Log.i("OTP is", "-----" + otp);

                String string = Config.CONFIRM_URL + mobile + val + phoneNumber.getText().toString() + and + otp1 + val + otp + and + brand + val + code;
                Log.i("Detail  is", "-----------" + string);

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, string,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String response) {
                                Log.i("response.....", "value-----++++++--" + response);

                                //ArrayList<Integer> minqtyList = new ArrayList<Integer>();
                                runOnUiThread(new Runnable() {

                                    public void run() {
                                        JSONObject jsonObject;
                                        try {
                                            jsonObject = new JSONObject(response);
                                            Log.i("Otp result", "========" + jsonObject);

                                            if (jsonObject.getBoolean("result")) {
                                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                                String s = jsonObject1.getString("user_id");

                                                Log.i("result", "value1" + jsonObject1.getString("user_id"));
                                                SharedPreferences pref = getApplicationContext().getSharedPreferences("DashBoard", 0);
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putString("userId", s);
                                                Log.i("result", "value1======" + s);
                                                editor.commit();
                                                loading.dismiss();
                                                startActivity(new Intent(OTPActivity.this, MainActivity.class));
                                                //dismissing the progressbar


                                            } else {
                                                //Displaying a toast if the otp entered is wrong
                                                Toast.makeText(OTPActivity.this, "Wrong OTP Please Try Again", Toast.LENGTH_LONG).show();
                                                loading.dismiss();
                                                confirmOtp();

                                               /* try {
                                                    //Asking user to enter otp again
                                                    confirmOtp();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }*/
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                        //  Log.i("response of S.....","value-----++++++--"+s);
                                        //if the server response is success
                               /* try {
                                    Log.i("Result","value-----++++++--"+jsonObject.getBoolean("result"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }*/
                                    }
                                });
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //loading.dismiss();
                                //finish();
                                //alertDialog.dismiss();
                                // buttonRegister.setVisibility(View.GONE);
                                Toast.makeText(OTPActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username

                        //  Log.e("code",code);
                        Log.e("otp", otp);
                        Log.e("Mobile", phoneNumber1.getText().toString().trim());
                        params.put(Config.KEY_PHONE, phoneNumber1.getText().toString().trim());
                        params.put(Config.KEY_OTP, otp);
                        params.put(Config.BRAND_CODE, code);

                        // params.put(Config.KEY_USERNAME, username);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);

            }
        });
    }

    /*@SuppressWarnings("deprecation")
    public void register(){

        final ProgressDialog loading = ProgressDialog.show(this, "Generating OTP", "Please wait...", false, false);
        contact = phoneNumber.getText().toString().trim();
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Contact","result");
            Log.i("JSON Object", "get---------" + jsonObject.toString());
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
            HttpClient client = new DefaultHttpClient(httpParams);

            String url = "http://socialcampaign.co.in/apps/event_management/services/evntSendOtpJson.php?contact=";

            HttpPost request = new HttpPost(url);
            request.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes("UTF8")));
            request.setHeader("jsonObject", jsonObject.toString());
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if(entity!= null){
                InputStream inputStream = entity.getContent();
               // String result = RestClient.convertStreamToString(inputStream);
            }
        }catch (Throwable t) {
            Toast.makeText(this, "Request failed: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }*/
    //this method will register the user

    @Override
    public void onClick(View v) {


        if (phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter a Valid Number", Toast.LENGTH_LONG).show();
        } else {
            String getOtp = Config.REGISTER_URL + phoneNumber.getText().toString();

            Log.e("GetOtp", getOtp);

            new JSONTask().execute(getOtp);
            // pref.setMobileNumber(mobile);
            /*if (checkPermission()) {
                if(!isFinishing());
            {
                // Log.i("permission status", "---------" + isFinishing());

                String getOtp = Config.REGISTER_URL + phoneNumber.getText().toString();

                Log.e("GetOtp", getOtp);

                new JSONTask().execute(getOtp);
                pref.setMobileNumber(mobile);


            }
            //register();
        }else {

            requestPermission();
        }*/
        }

        //Toast.makeText(OTPActivity.this, "Permissions Granted", Toast.LENGTH_LONG).show();


    }


   /* public void register(){


        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Generating OTP", "Please wait...", false, false);


        //Getting user data
           *//* username = editTextUsername.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();*//*
        contact = phoneNumber.getText().toString().trim();
        Log.i("Phone Number", "get---------" + contact.toString());


        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
                new Response.Listener<String>() {
                    @SuppressWarnings({"deprecation", "unused"})
                    @Override
                    public void onResponse(final String response) {
                        loading.dismiss();
                        HandlerThread thread = new HandlerThread("MyThread") {
                            @Override
                            public void run() {
                                Looper.prepare();

                                handler = new Handler(Looper.myLooper());
                                handler.postDelayed(new Runnable() {

                                    public void run() {
                                        try {
                                            //Creating the json object from the response
                                            JSONObject jsonObject = new JSONObject(response);
                                            Log.i("JSON Object", "get---------" + jsonObject);
                                            jsonObject.put("contact", "result");
                                            Log.i("JSON Object", "get---------" + jsonObject);
                                            HttpParams httpParams = new BasicHttpParams();
                                            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
                                            HttpClient client = new DefaultHttpClient(httpParams);

                                            // String url = "http://socialcampaign.co.in/apps/event_management/services/evntSendOtpJson.php?contact=";

                                            HttpPost request = new HttpPost(Config.REGISTER_URL);
                                            request.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes("UTF8")));
                                            request.setHeader("jsonObject", jsonObject.toString());
                                            HttpResponse responsee = client.execute(request);
                                            HttpEntity entity = responsee.getEntity();
                                            // String error = jsonResponse.getString("Success");
                                            // Log.i("JSON Object", "get#########" + error);
                                            //If it is success
                                    *//*if(entity!=null){
                                        InputStream instream = entity.getContent();
                                        Log.i("Value of ", "instream----------" + instream);

                                        //  String result = RestClient.convertStreamToString(instream);
                              *//**//*  Log.i("Read from server", result);
                                Toast.makeText(OTPActivity.this,  result,
                                        Toast.LENGTH_LONG).show();*//**//*
                                    }*//*
                                            if (jsonObject.getBoolean("result")) {
                                                //Asking user to confirm otp
                                                confirmOtp();

                                                Log.i("confirm otp", "get----");
                                            } else {
                                                //If not successful user may already have registered
                                                Toast.makeText(OTPActivity.this, "Phone number already registered", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (Throwable t) {
                                            t.printStackTrace();
                                        }
                                    }
                                }, 2000);
                                Looper.loop();
                            }

                        };

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(OTPActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                  *//*  params.put(Config.KEY_USERNAME, username);
                    params.put(Config.KEY_PASSWORD, password);*//*
                params.put(Config.KEY_PHONE, contact);
                return params;
            }
        };

        //Adding request the the queue
      //  MyApplication.getInstance().addToRequestQueue(stringRequest);
        requestQueue.add(stringRequest);
    }*/

    public boolean checkPermission() {


        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_SMS);

        return SecondPermissionResult == PackageManager.PERMISSION_GRANTED;

    }




        /*if (checkPermission()) {

            Toast.makeText(OTPActivity.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();

        } else {

            requestPermission();
        }*/
    //Calling register method on register button click

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_SMS}, PERMISSION_REQUEST_CODE);
       /* String getOtp = Config.REGISTER_URL + phoneNumber.getText().toString();
        new JSONTask().execute(getOtp);
*/
        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED)
                {
                       *//* RECEIVE_SMS,
                        READ_SMS;*//*

                    // register();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)){

                        }
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_SMS},
                                READ_CONTACTS_PERMISSIONS_REQUEST);
                        *//*String getOtp = Config.REGISTER_URL + phoneNumber.getText().toString();
                        new JSONTask().execute(getOtp);*//*
                    }
                }*/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean readmsg = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (readmsg) {
                        Toast.makeText(OTPActivity.this, "Permissions Granted", Toast.LENGTH_LONG).show();
                        // Snackbar.make(OTPActivity.this, "Permission Granted", Snackbar.LENGTH_LONG).show();
                        String getOtp = Config.REGISTER_URL + phoneNumber.getText().toString();
                        new JSONTask().execute(getOtp);
                        pref.setMobileNumber(mobile);
                    } else {
                        Toast.makeText(OTPActivity.this, "Permissions Denied", Toast.LENGTH_LONG).show();
                        //Snackbar.make(view, "Permission Denied", Snackbar.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_SMS)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{READ_SMS},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }

           /* *//*case RequestPermissionCode:*//*
            if(requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                } else {

                    // boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                   // boolean ReadReceivePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadSmsPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    //boolean ReadSmsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (ReadSmsPermission) {
                        Toast.makeText(OTPActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(OTPActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }
            }else{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }*/

                /*break;*/
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(OTPActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public class JSONTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            nProgressDialog = new ProgressDialog(OTPActivity.this);
            nProgressDialog.setMessage("Generating OTP..");
            nProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            nProgressDialog.setCancelable(false);
            nProgressDialog.show();

            //showDialog(DIALOG_DOWNLOAD_PROGRESS);
            //filedele();

        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;


            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
               /* connection.setDoOutput(true);
                connection.setDoInput(true);*/

                InputStream stream = connection.getInputStream();

                Log.i("result", "value=================+++++++++++++++" + stream);
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    Log.i("Line value", "is======" + line);

                    buffer.append(line);
                   /* if(line.equals("result")){

                    }*/

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
            //Toast.makeText(OTPActivity.this, "Number is Not Registered...!", Toast.LENGTH_LONG).show();

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            Log.e("TAG", "progress2==" + progress[0]);
            nProgressDialog.setProgress(Integer.parseInt(progress[0]));

        }

        @SuppressLint("LogConditional")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //textView.setText(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.i("result", "jsonobject=======" + jsonObject);
                if (jsonObject.getBoolean("result")) {

                    JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("Dash", 0);
                    SharedPreferences.Editor editor1 = preferences.edit();
                    String s1 = jsonObject2.getString("otp");

                    editor1.putString("otp", s1);
                    editor1.commit();
                    Log.i("result", "otp----===" + jsonObject2.getString("otp"));
                    Log.i("result", "value0" + jsonObject);
                    // Toast.makeText(OTPActivity.this, "Number is Not Registered...!", Toast.LENGTH_LONG).show();
                    nProgressDialog.dismiss();
                    confirmOtp();

                } else {
                    Toast.makeText(OTPActivity.this, "User is not registered...!", Toast.LENGTH_LONG).show();
                    nProgressDialog.dismiss();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    //Calling register method on register button click
}
















   /* private void tryAndPrefillPhoneNumber() {
        if (checkCallingOrSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber.setText(manager.getLine1Number());
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            tryAndPrefillPhoneNumber();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "This application needs permission to read your phone number to automatically "
                        + "pre-fill it", Toast.LENGTH_LONG).show();
            }
        }
    }
}*/

   /* private void openActivity(String phoneNumber) {
        Intent verification = new Intent(this, VerificationActivity.class);
        verification.putExtra(INTENT_PHONENUMBER, phoneNumber);

        startActivity(verification);
    }

    private void setButtonsEnabled(boolean enabled) {
        mSmsButton.setEnabled(enabled);
    }

    public void onButtonClicked(View view) {
        openActivity(getE164Number());
    }

    private void resetNumberTextWatcher(String countryIso) {

        if (mNumberTextWatcher != null) {
            mPhoneNumber.removeTextChangedListener(mNumberTextWatcher);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mNumberTextWatcher = new PhoneNumberFormattingTextWatcher(countryIso) {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    super.onTextChanged(s, start, before, count);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    super.beforeTextChanged(s, start, count, after);
                }

                @Override
                public synchronized void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    if (isPossiblePhoneNumber()) {
                        setButtonsEnabled(true);
                        mPhoneNumber.setTextColor(Color.BLACK);
                    } else {
                        setButtonsEnabled(false);
                        mPhoneNumber.setTextColor(Color.RED);
                    }
                }
            };
        }

        mPhoneNumber.addTextChangedListener(mNumberTextWatcher);
    }

    private boolean isPossiblePhoneNumber() {
        return PhoneNumberUtils.isPossibleNumber(mPhoneNumber.getText().toString());
    }

    private String getE164Number() {
        return mPhoneNumber.getText().toString().replaceAll("\\D", "").trim();
        // return PhoneNumberUtils.formatNumberToE164(mPhoneNumber.getText().toString(), mCountryIso);
    }*/

