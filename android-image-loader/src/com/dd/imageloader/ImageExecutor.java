package com.dd.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageExecutor {
	private Executor executor;
	private ImageLoader mediaLoader;
	private Handler handler;

	public ImageExecutor(ImageLoader mediaLoader) {
		this(mediaLoader, Executors.newSingleThreadExecutor());
	}

	public ImageExecutor(ImageLoader mediaLoader, Executor executor) {
		if (!isUiThread()) {
			throw new IllegalArgumentException(getClass().toString() + " must be created in UI Thread");
		}

		this.mediaLoader = mediaLoader;
		this.executor = executor;
		this.handler = new Handler();
	}

	public void execute(final ImageView imageView, final String path) {
		imageView.setTag(path.hashCode());

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (isViewScrolled(imageView, path)) {
					return;
				}

				Bitmap bitmap = mediaLoader.loadBitmap(path);

				if (isViewScrolled(imageView, path)) {
					return;
				}

				displayImage(imageView, bitmap);
			}
		};

		executor.execute(runnable);
	}

	private void displayImage(final ImageView imageView, final Bitmap bitmap) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				imageView.setImageBitmap(bitmap);
			}
		});
	}

	private boolean isViewScrolled(ImageView imageView, String path) {
		return path.hashCode() != (Integer) imageView.getTag();
	}

	private boolean isUiThread() {
		return Looper.getMainLooper().getThread() == Thread.currentThread();
	}

}
