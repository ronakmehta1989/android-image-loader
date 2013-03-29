package com.dd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.dd.imageloader.ImageExecutor;
import com.dd.imageloader.ImageLoader;
import com.dd.util.PropertiesUtils;

import java.util.List;
// TODO not implemented yet
public class GridViewAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private final List<String> list;

	private ImageExecutor ex;
	private Context context;
	private int displayWidth;

	public GridViewAdapter(Context context, List<String> list, ImageLoader imageLoader) {
		this.context = context;
		this.ex = new ImageExecutor(imageLoader);
		this.inflater = LayoutInflater.from(context);
		this.list = list;
		this.displayWidth = PropertiesUtils.getDisplayWidth(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int thePosition) {

		return thePosition;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;

		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(displayWidth / 2, displayWidth / 2));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}

		String item = getItem(position);

		imageView.setImageBitmap(null);

//		ImageItem imageItem = new ImageItem();
//		imageItem.setImage(imageView);
//		imageItem.setPath(item);
//		ex.execute(imageItem);

		return imageView;
	}

}