package com.dd.util.log;

import android.util.Log;

public final class L {

	public static void e(final String msg) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();

		String callerClassName = elements[1].getClassName();
		String callerMethodName = elements[1].getMethodName();

		String TAG = "[" + callerClassName + "]";
		Log.e(TAG, "[" + callerMethodName + "] " + msg);
	}

	public static void d(final String msg) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();

		String callerClassName = elements[1].getClassName();
		String callerMethodName = elements[1].getMethodName();

		String TAG = "[" + callerClassName + "]";

		Log.d(TAG, "[" + callerMethodName + "] " + msg);
	}

	public static void w(final String msg) {
		Throwable t = new Throwable();
		StackTraceElement[] elements = t.getStackTrace();

		String callerClassName = elements[1].getFileName();
		String callerMethodName = elements[1].getMethodName();

		String TAG = "[" + callerClassName + "]";

		Log.w(TAG, "[" + callerMethodName + "] " + msg);
	}

}
