package com.dd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import com.dd.adapters.GridViewAdapter;
import com.dd.imageloader.ImageLoader;
import com.dd.imageloader.decorator.DiskCacheImageLoader;
import com.dd.imageloader.decorator.MemoryCacheImageLoader;
import com.dd.imageloader.decorator.UrlImageLoader;
import com.dd.loader.image.R;

import java.util.List;

// TODO not implemented yet
public class GridActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.grid_view);

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

		ImageLoader loader = new UrlImageLoader();

		if (useDiskCache) {
			loader = new DiskCacheImageLoader(loader, Settings.getCacheFolder());
		}

		if (useMemoryCache) {
			loader = new MemoryCacheImageLoader(loader, Settings.getMemoryCacheSize(this));
		}

		GridViewAdapter adapter = new GridViewAdapter(this, imageUrlList, loader);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(adapter);


	}
}
