package modelmayhem.com.modelmayhem.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import modelmayhem.com.modelmayhem.R;


public class DialogUtility {
	
	public static void showMessageWithOk(final String message, final Activity mActivity) {
		try{
			if(mActivity == null)
				return;
			mActivity.runOnUiThread(new Runnable() {
	
				@Override
				public void run() {
					AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
					dialog.setMessage(message);
					dialog.setTitle(R.string.app_name);
					dialog.setNeutralButton("OK", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					dialog.show();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void showMessegeWithOk(final String message, final Activity mActivity) {
		if(mActivity == null)
			return;
		mActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
				dialog.setMessage(message);
				dialog.setTitle(R.string.app_name);
				dialog.setNeutralButton("OK", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
						mActivity.finish();
					}
				});
				dialog.show();
			}
		});
	}

	public static void showMessageOkWithCallback(String message, Activity mContext, final AlertDialogCallBack callback) {
		if(mContext == null)
			return;
		final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setMessage(message);
		dialog.setCancelable(false);
		dialog.setTitle(R.string.app_name);
		dialog.setNegativeButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onSubmit();
			}
		});
		mContext.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog.show();
			}
		});
	}

	public static void showCallbackMessage(String message, Activity mContext, final AlertDialogCallBack callback) {
		if(mContext == null)
			return;
		final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setMessage(message);
		dialog.setTitle(R.string.app_name);
		dialog.setNegativeButton("No", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onSubmit();
			}
		});

		dialog.setPositiveButton("Yes", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onCancel();
			}
		});

		mContext.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog.show();
			}
		});
	}
	
	
	public static void showCallbackMessageNoYes(String message, Activity mContext, final AlertDialogCallBack callback) {
		if(mContext == null)
			return;
		final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setMessage(message);
		dialog.setTitle(R.string.app_name);
		

		dialog.setPositiveButton("No", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onCancel();
			}
		});
		
		dialog.setNegativeButton("Yes", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onSubmit();
			}
		});

		mContext.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog.show();
			}
		});
	}



	public static void dissmissdialog(Activity act){
		if(act == null)
			return;
		View decorView = act.getWindow().getDecorView();
		if (decorView != null) {
			int uiOptions =   View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
					| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			decorView.setSystemUiVisibility(uiOptions);
		}
	}
	public static int getPixelValue(Context context, int dimenId) {
		Resources resources = context.getResources();
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				dimenId,
				resources.getDisplayMetrics()
		);
	}

}
