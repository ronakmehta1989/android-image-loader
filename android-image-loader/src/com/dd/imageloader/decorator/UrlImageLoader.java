package com.dd.imageloader.decorator;

import android.graphics.Bitmap;
import com.dd.imageloader.ImageLoader;
import com.dd.util.BitmapUtils;

public class UrlImageLoader implements ImageLoader {
	@Override
	public Bitmap loadBitmap(String path) {
		return BitmapUtils.createFromUrl(path);
	}
}
