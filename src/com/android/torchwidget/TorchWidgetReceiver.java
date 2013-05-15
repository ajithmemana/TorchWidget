package com.android.torchwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RemoteViews;
import android.widget.Toast;

public class TorchWidgetReceiver extends BroadcastReceiver {
	private static boolean isLightOn = false;
	private static Camera camera;

	@Override
	public void onReceive(Context context, Intent intent) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.activity_main);

		// if(isLightOn) {
		// views.setImageViewResource(R.id.button1,
		// R.drawable.btn_star_off_normal_holo_light);
		// } else {
		// views.setImageViewResource(R.id.button1,
		// R.drawable.btn_star_off_pressed_holo_light);
		// }

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		appWidgetManager.updateAppWidget(new ComponentName(context,
				TorchWidgetProvider.class), views);

		if (isLightOn) {
			if (camera != null) {
				camera.stopPreview();
				camera.release();
				camera = null;
				isLightOn = false;
			}

		} else {
			// Open the default i.e. the first rear facing camera.
			camera = Camera.open();

			if (camera == null) {
				Toast.makeText(context, "no camera", Toast.LENGTH_SHORT).show();
			} else {
				// Set the torch flash mode
				Parameters param = camera.getParameters();
				param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				try {
					camera.setParameters(param);
					camera.startPreview();
					isLightOn = true;
				} catch (Exception e) {
					Toast.makeText(context, "no flash", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}
}