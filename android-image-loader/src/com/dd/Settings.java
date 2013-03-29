package com.dd;

import android.content.Context;
import android.os.Environment;
import com.dd.util.MemoryUtils;

import java.io.File;

public class Settings {
	private static final String CACHE_FOLDER_NAME = "TEST_IMAGE_CACHE_FOLDER";

	public static File getCacheFolder() {
		File folder = new File(Environment.getExternalStorageDirectory(), CACHE_FOLDER_NAME);
		folder.mkdirs();

		return folder;
	}

	public static int getMemoryCacheSize(Context context) {
		int totalFreeMemory = MemoryUtils.getAvailableMemory(context) * 1024 * 1024;
		return totalFreeMemory / 2;
	}

}
