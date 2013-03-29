package com.dd.util;

import android.app.ActivityManager;
import android.content.Context;

public final class MemoryUtils {

	public static int getAvailableMemory(Context context) {
		int availableMemory = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

		return availableMemory;
	}
}
