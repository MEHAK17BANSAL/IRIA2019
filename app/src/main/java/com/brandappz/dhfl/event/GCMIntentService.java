package com.brandappz.dhfl.event;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import com.brandappz.dhfl.event.activities.MainActivity;
import com.brandappz.framework.AppLog;
import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = GCMIntentService.class.getSimpleName();
	public static final String EXTRA_MESSAGE = "message";
	public static final String SENDER_ID = "426871778008";

	public static final String DISPLAY_MESSAGE_ACTION = "com.brandappz.dhfl.event.DISPLAY_MESSAGE";

	public GCMIntentService() {
		super(SENDER_ID);
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		AppLog.logMessage(TAG, "Device registered: regId = " + registrationId);
		// SessionVO.setNotifyRegId(registrationId);
		// if(SessionVO.getUserId() >0){
		// ServerManager serverManager = new ServerManager(this);
		// serverManager.checkVersion();
		// }

		// displayMessage(context, "Your device registered with GCM");
		// displayMessage(context, "Welcome to Event!!");
		// Log.d("NAME", MainActivity.name);
		// ServerUtilities.register(context, MainActivity.name,
		// MainActivity.email, registrationId);
		 generateNotification(context, "Welcome to Event!! <br> Wow");
	}

	/**
	 * Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		AppLog.logMessage(TAG, "Device unregistered");
		displayMessage(context, getString(R.string.gcm_unregistered));
		// ServerUtilities.unregister(context, registrationId);
		generateNotification(context, "We are going to miss you!! <br>");
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		AppLog.logMessage(TAG, "Received message");
		String message = intent.getExtras().getString("message");

		displayMessage(context, message);
		// notifies user
		generateNotification(context, message);
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		AppLog.logMessage(TAG, "Received deleted messages notification");
		String message = getString(R.string.gcm_deleted, total);
		displayMessage(context, message);
		// notifies user
		generateNotification(context, message);
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		AppLog.logMessage(TAG, "Received error: " + errorId);
		displayMessage(context, getString(R.string.gcm_error, errorId));
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// log message
		AppLog.logMessage(TAG, "Received recoverable error: " + errorId);
		displayMessage(context,
				getString(R.string.gcm_recoverable_error, errorId));
		return super.onRecoverableError(context, errorId);
	}

	private void generateNotification(Context context, String message) {
		AppLog.logMessage(TAG, "generateNotification:message: " + message);
		if (null == message)
			return;
		String notMsg = message;
		if (message.contains("##")) {
			String[] messageText = message.split("##");
			// ServerManager serverManager = new ServerManager(context);
			// serverManager.updateNotification(messageText[0],"delivered");
			notMsg = messageText[1];
		}
		String[] contentText = notMsg.split("<br>");

		AppLog.logMessage("GCMIntent", "contentText:" + contentText.length);
		createNotification(context, contentText[0], contentText[0], contentText);
		
//		createNotification(context, contentText[0], contentText);

	}

	private void createNotification(Context context1, CharSequence tickerText,
			CharSequence contentTitle, String[] contentText) {

		int icon = R.drawable.ic_launcher;
		// long when = System.currentTimeMillis();
		Intent notificationIntent = new Intent(context1, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context1, 0,
				notificationIntent, 0);
		// Notification notification = new Notification(icon, tickerText, when);
		// notification.setLatestEventInfo(context1, contentTitle, contentText,
		// contentIntent);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context1).setSmallIcon(icon).setContentTitle(contentTitle);
		mBuilder.setAutoCancel(true);
//		mBuilder.setContentIntent(PendingIntent.getBroadcast(context1, 0, new Intent(NOTIFICATION_CLICKED_ACTION), 0));
//	    mBuilder.setDeleteIntent(PendingIntent.getBroadcast(context1, 0, new Intent(NOTIFICATION_DELETED_ACTION), 0));

		mBuilder.setSound(RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		//notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		// String[] events = new String[6];
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle(contentTitle);
		// Moves events into the expanded layout
		for (int i = 0; i < contentText.length; i++) {

			inboxStyle.addLine(contentText[i]);
		}
		// Moves the expanded layout object into the notification object.
		mBuilder.setStyle(inboxStyle);
		mBuilder.setContentIntent(contentIntent);

		// and this
		NotificationManager mNotificationManager = (NotificationManager) context1
				.getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(1, mBuilder.build());
	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	private static void generateNotificationSingle(Context context,
			String message) {
		/*int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, MainActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// notification.sound = Uri.parse("android.resource://" +
		// context.getPackageName() + "your_sound_file_name.mp3");

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		// notification.setStyle(new
		// NotificationCompat.BigTextStyle().bigText(message));

		notificationManager.notify(0, notification);
*/
	}

	private static void generateNotificationMulti(Context context,
			String message) {

		String contentTitle = context.getString(R.string.app_name);
		createNotification(context, contentTitle, message);
	}

	public static void createNotification(Context context1,
			CharSequence contentTitle, String message) {

		// contentTitle = contentTitle+" 123";
		AppLog.logMessage("GCMIntent", "Message:" + message);
		// String[] contentText = message.split("<br>");
		// AppLog.logMessage("GCMIntent", "contentText:"+contentText.length);
		int icon = R.drawable.ic_launcher;
		// long when = System.currentTimeMillis();
		Intent notificationIntent = new Intent(context1, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context1, 0,
				notificationIntent, 0);
		// Notification notification = new Notification(icon, tickerText, when);
		// notification.setLatestEventInfo(context1, contentTitle, contentText,
		// contentIntent);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context1).setSmallIcon(icon).setContentTitle(contentTitle);

		Notification notification = mBuilder
				.setSmallIcon(icon)
				.setTicker(contentTitle)
				.setWhen(0)
				.setAutoCancel(true)
				.setContentTitle(contentTitle)
				.setStyle(
						new NotificationCompat.BigTextStyle().bigText(message))
				.setContentIntent(contentIntent)
				.setSound(
						RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.setLargeIcon(
						BitmapFactory.decodeResource(context1.getResources(),
								icon)).setContentText(message).build();

		// NotificationCompat.InboxStyle inboxStyle = new
		// NotificationCompat.InboxStyle();
		// // String[] events = new String[6];
		// // Sets a title for the Inbox in expanded layout
		// inboxStyle.setBigContentTitle(contentTitle);
		// // Moves events into the expanded layout
		// for (int i = 0; i < contentText.length; i++) {
		// inboxStyle.addLine(contentText[i]);
		// AppLog.logMessage("GCMIntent", "contentText:i:"+contentText[i]);
		// }
		// inboxStyle.addLine(makeNotificationLine("UserXYZ", "How are you?"));
		// // Moves the expanded layout object into the notification object.
		// mBuilder.setStyle(inboxStyle);
		// mBuilder.setContentIntent(contentIntent);

		// mBuilder.setNumber(contentText.length);

		// mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
		//
		// // Play default notification sound
		// mBuilder.getNotification().defaults |= Notification.DEFAULT_SOUND;
		//
		// //notification.sound = Uri.parse("android.resource://" +
		// context.getPackageName() + "your_sound_file_name.mp3");
		//
		// // Vibrate if vibrate is enabled
		// mBuilder.getNotification().defaults |= Notification.DEFAULT_VIBRATE;
		// notificationManager.notify(0, notification);

		// and this
		NotificationManager mNotificationManager = (NotificationManager) context1
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// mNotificationManager.notify(0, mBuilder.build());
		mNotificationManager.notify(0, notification);
	}

	private static final StyleSpan mBoldSpan = new StyleSpan(Typeface.BOLD);

	private static SpannableString makeNotificationLine(String title,
			String text) {
		final SpannableString spannableString;
		if (title != null && title.length() > 0) {
			spannableString = new SpannableString(String.format("%s  %s",
					title, text));
			spannableString.setSpan(mBoldSpan, 0, title.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			spannableString = new SpannableString(text);
		}
		return spannableString;
	}

	static void displayMessage(Context context, String message) {
		Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
		intent.putExtra(EXTRA_MESSAGE, message);
		context.sendBroadcast(intent);
	}


}
