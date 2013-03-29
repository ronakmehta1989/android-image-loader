package com.dd.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public final class PropertiesUtils {
	public static Display getDisplay(Context context) {
		final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display;
	}

	public static int getDisplayWidth(Context context) {
		final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}

	public static int getDisplayHeight(Context context) {
		final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
}
