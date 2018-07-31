package com.brandappz.dhfl.event.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.provider.Settings.Secure;
import android.widget.Toast;

import com.brandappz.framework.AppLog;
import com.brandappz.framework.AsyncPostHandler;
import com.brandappz.framework.BaseUtil;
import com.brandappz.framework.URLAsyncTask;
import com.google.android.gcm.GCMRegistrar;

public class ServerManager implements AsyncPostHandler {
	final public static String DOMAIN_BASE_URL = "http://www.socialcampaign.co.in/";
	
	final public static String BASE_URL = DOMAIN_BASE_URL + "apps/event_management/";
	final public static String CHECK_UPD_URL = BASE_URL + "checkUpdate.php";
	
	private static final int CHECK_UPD_QUERY = 1;

	final static String ENCODING = "UTF-8";
	final static String VERSION = "0.01";
	final static String SERVER_VERSION = "ver="+VERSION;

	Context context = null;

	public ServerManager(Context context) {
		this.context = context;
	}
	
	public void checkVersion() {
		//http://www.socialcampaign.co.in/apps/event_management/checkUpdate.php?ver=0.01&mac_unique_id=9680&key=E010228&notifyRegId=ABs900
		try {
			URLAsyncTask urlAsyncTask = new URLAsyncTask(CHECK_UPD_QUERY, this);
			StringBuffer param = new StringBuffer(SERVER_VERSION);

			param.append("&mac_unique_id=").append(encodeData(Secure.getString(context.getContentResolver(), Secure.ANDROID_ID)));
//			param.append("&key=").append(SessionVO.getAccessToken());
			param.append("&notifyRegId=").append(encodeData(GCMRegistrar.getRegistrationId(context.getApplicationContext())));
			param.append("&model=").append(encodeData(android.os.Build.MODEL));
			param.append("&andVer=").append(encodeData(android.os.Build.VERSION.RELEASE));
			AppLog.logMessage("ServerManager::checkVersion",
					"param:" + param.toString());
			urlAsyncTask.execute(CHECK_UPD_URL, param.toString());
		} catch (Exception e) {
			Toast.makeText(context, "Error: " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
	
	
	public static String encodeData(String data)
			throws UnsupportedEncodingException {
		return URLEncoder.encode((null == data ? "" : data), ENCODING);
	}

	@Override
	public void processData(int whichQuery, String result) {
		// Toast.makeText(rdsLogin, "Response: " + result,
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPostExecute(int whichQuery, String result) {
		AppLog.logMessage("ServerManager::processData", "onPostExecute:whichQuery"+whichQuery+":result:" + result+":");
		result = result.trim();
		AppLog.logMessage("ServerManager::processData", "onPostExecute:whichQuery"+whichQuery+":after trim result:" + result+":");
		switch (whichQuery) {
		case CHECK_UPD_QUERY:
			String msg = processUpdate(result);
			if(msg.startsWith("Error")){
				BaseUtil.showErrorAlertDialog(context, msg, true);
			}
//			if(!(context instanceof Activity))
//				return;
//			((MainActivity) context).doCheckUpdateProcessing(result);
			break;
		default:
		}
	}

	@Override
	public void doOnException(int arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	public String processUpdate(String data) {
		String retMsg = "Err";
		try {
			JSONObject jarray = new JSONObject(data);
			retMsg = jarray.getString("msg");
//			JSONObject objJson = jarray.getJSONObject("msg");
			AppLog.logMessage("ServerManager::processUpdate",
					"processUpdate:Message" + retMsg/*objJson.toString()*/);
//			return objJson.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retMsg;

	}

	
}
