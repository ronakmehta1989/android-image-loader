package com.dd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dd.imageloader.ImageExecutor;
import com.dd.imageloader.ImageLoader;
import com.dd.loader.image.R;

import java.util.List;
import java.util.concurrent.Executors;

public class ListViewAdapter extends BaseAdapter {

	private static final class ViewHolder {
		TextView textLabel;
		ImageView imageView;
	}

	private final LayoutInflater inflater;
	private final List<String> pathList;

	private ImageExecutor ex;

	public ListViewAdapter(Context context, List<String> pathList, ImageLoader imageLoader, int threadsCount) {
		ex = new ImageExecutor(imageLoader, Executors.newFixedThreadPool(threadsCount));
		inflater = LayoutInflater.from(context);
		this.pathList = pathList;
	}

	@Override
	public int getCount() {
		return pathList.size();
	}

	@Override
	public String getItem(int position) {
		return pathList.get(position);
	}

	@Override
	public long getItemId(int thePosition) {

		return thePosition;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View resultView = convertView;

		ViewHolder holder;

		if (resultView == null) {
			resultView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.textLabel = (TextView) resultView.findViewById(R.id.textView);
			holder.imageView = (ImageView) resultView.findViewById(R.id.imageView);

			resultView.setTag(holder);
		} else {
			holder = (ViewHolder) resultView.getTag();
		}

		final String path = getItem(position);
		holder.textLabel.setText(path.toString());
		holder.imageView.setImageBitmap(null);
		ex.execute(holder.imageView, path);

		return resultView;
	}

}