package com.dd.imageloader.decorator;

import android.graphics.Bitmap;
import android.util.LruCache;
import com.dd.imageloader.ImageLoader;

public class MemoryCacheImageLoader implements ImageLoader {
	private LruCache<String, Bitmap> memoryCache;
	private ImageLoader imageLoader;

	public MemoryCacheImageLoader(ImageLoader imageLoader, int cacheSize) {
		this.imageLoader = imageLoader;

		memoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}

	@Override
	public Bitmap loadBitmap(String path) {
		String key = String.valueOf(path.hashCode());
		Bitmap bitmap = getBitmapFromMemCache(key);

		if (bitmap == null) {
			bitmap = imageLoader.loadBitmap(path);

			if (bitmap != null) {
				addBitmapToMemoryCache(key, bitmap);
			}
		}

		return bitmap;
	}

	public void clean() {

		if (memoryCache == null) {
			return;
		}

		memoryCache.evictAll();
	}

	private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			memoryCache.put(key, bitmap);
		}
	}

	private Bitmap getBitmapFromMemCache(String key) {
		return memoryCache.get(key);
	}
}
