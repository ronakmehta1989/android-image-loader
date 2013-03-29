package com.dd.imageloader.decorator;

import android.graphics.Bitmap;
import com.dd.imageloader.ImageLoader;
import com.dd.util.BitmapUtils;
import com.dd.util.IOUtils;
import com.dd.util.log.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DiskCacheImageLoader implements ImageLoader {
	private ImageLoader imageLoader;
	private File cacheFolder;

	public DiskCacheImageLoader(ImageLoader imageLoader, File cacheFolder) {
		this.imageLoader = imageLoader;
		this.cacheFolder = cacheFolder;
	}

	@Override
	public Bitmap loadBitmap(String path) {
		Bitmap bitmap;
		String imageName = String.valueOf(path.hashCode());

		File file = new File(cacheFolder, imageName);

		if (file.exists()) {
			bitmap = BitmapUtils.createFromPath(file.getAbsolutePath());
		} else {
			bitmap = imageLoader.loadBitmap(path);
			saveBitmap(bitmap, file);

		}

		return bitmap;
	}

	private void saveBitmap(Bitmap bitmap, File file) {
		if (bitmap == null || file == null) {
			return;
		}
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outStream);
		} catch (FileNotFoundException e) {
			L.e(e.toString());
		} finally {
			IOUtils.close(outStream);
		}

	}

	public void clean() {

		File[] fileList = cacheFolder.listFiles();

		if (fileList == null) {
			return;
		}

		for (File f : fileList) {
			f.delete();
		}
	}
}
