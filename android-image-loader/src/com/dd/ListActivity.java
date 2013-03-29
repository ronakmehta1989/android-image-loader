package com.dd;

import android.content.Intent;
import android.os.Bundle;
import com.dd.adapters.ListViewAdapter;
import com.dd.imageloader.ImageLoader;
import com.dd.imageloader.decorator.DiskCacheImageLoader;
import com.dd.imageloader.decorator.MemoryCacheImageLoader;
import com.dd.imageloader.decorator.UrlImageLoader;

import java.util.List;

public class ListActivity extends android.app.ListActivity {
	private DiskCacheImageLoader diskCacheImageLoader;
	private MemoryCacheImageLoader memoryCacheImageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new ImageHandler(this).loadImageList(new ImageHandler.OnLoadCompleteListener() {
			@Override
			public void OnLoadComplete(List<String> imageUrlList) {
				initView(imageUrlList);
			}
		});
	}

	private void initView(List<String> imageUrlList) {
		Intent intent = getIntent();
		boolean useDiskCache = intent.getBooleanExtra(MainActivity.USE_DISK_CACHE, false);
		boolean useMemoryCache = intent.getBooleanExtra(MainActivity.USE_MEMORY_CACHE, false);
		int threadsCount = intent.getIntExtra(MainActivity.NUMBER_OF_THREADS, 1);

		ImageLoader loader = new UrlImageLoader();

		if (useDiskCache) {
			diskCacheImageLoader = new DiskCacheImageLoader(loader, Settings.getCacheFolder());
		}

		if(diskCacheImageLoader != null){
			loader = diskCacheImageLoader;
		}

		if (useMemoryCache) {
			memoryCacheImageLoader = new MemoryCacheImageLoader(loader, Settings.getMemoryCacheSize(this));
		}

		if(memoryCacheImageLoader != null){
			loader = memoryCacheImageLoader;
		}

		ListViewAdapter adapter = new ListViewAdapter(this, imageUrlList, loader, threadsCount);
		setListAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (diskCacheImageLoader != null) {
			diskCacheImageLoader.clean();
		}

		if (memoryCacheImageLoader != null) {
			memoryCacheImageLoader.clean();
		}
	}

}
