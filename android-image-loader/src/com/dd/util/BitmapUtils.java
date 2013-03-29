package com.dd.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class BitmapUtils {

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of imageloader
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (reqWidth <= 0 || reqHeight <= 0) {
			return inSampleSize;
		}

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap createFromPath(String path) {
		return createFromPath(path, 0, 0);
	}

	public static Bitmap createFromPath(String path, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = null;

		if (reqWidth > 0 || reqHeight > 0) {
			// First decode with inJustDecodeBounds=true to check dimensions
			options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
		}

		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap createFromResources(Resources res, int resId) {
		return createFromResources(res, resId, 0, 0);
	}

	public static Bitmap createFromResources(Resources res, int resId, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = null;

		if (reqWidth > 0 || reqHeight > 0) {
			// First decode with inJustDecodeBounds=true to check dimensions
			options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(res, resId, options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
		}

		return BitmapFactory.decodeResource(res, resId, options);
	}


	public static Bitmap createFromUrl(String url) {
		return createFromUrl(url, 0, 0);
	}

	public static Bitmap createFromUrl(String url, int reqWidth, int reqHeight) {
		InputStream stream = IOUtils.getStream(url);

		BitmapFactory.Options options = null;

		if (reqWidth > 0 || reqHeight > 0) {
			// First decode with inJustDecodeBounds=true to check dimensions
			options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(stream, null, options);

			stream = IOUtils.getStream(url);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
		}

		return BitmapFactory.decodeStream(stream, null, options);
	}
}
