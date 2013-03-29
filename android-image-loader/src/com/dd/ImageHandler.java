package com.dd;

import android.content.Context;
import android.os.AsyncTask;
import com.dd.util.HttpClient;
import com.dd.util.IOUtils;
import com.dd.util.JSONUtils;
import com.dd.util.NetworkUtils;
import com.dd.util.log.L;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageHandler {

	public interface OnLoadCompleteListener {
		public void OnLoadComplete(List<String> imageUrlList);
	}

	private Context context;

	public ImageHandler(Context context) {
		this.context = context;
	}

	private List<String> parseImageList(String url) {
		List<String> imageUrlList = new ArrayList<String>();

		String jsonString = IOUtils.toString(HttpClient.get(url));
		try {
			JSONObject json = new JSONObject(jsonString);
			JSONArray shots = JSONUtils.parseArray(json, "shots");

			for (int i = 0; i < shots.length(); i++) {
				String imageUrl = JSONUtils.parseString(shots.getJSONObject(i), "image_teaser_url");
				imageUrlList.add(imageUrl);
			}

		} catch (JSONException e) {
			L.e(e.toString());
		}

		return imageUrlList;
	}

	public void loadImageList(final OnLoadCompleteListener listener) {
		if (!NetworkUtils.isWIFIOn(context)) {
			return;
		}
		new AsyncTask<Void, Void, List<String>>() {

			@Override
			protected List<String> doInBackground(Void... voids) {
				List<String> imageUrlList = new ArrayList<String>();
				imageUrlList.addAll(parseImageList("http://api.dribbble.com/shots/everyone"));
				imageUrlList.addAll(parseImageList("http://api.dribbble.com/shots/debuts"));
				imageUrlList.addAll(parseImageList("http://api.dribbble.com/shots/popular"));
				return imageUrlList;
			}

			@Override
			protected void onPostExecute(List<String> imageUrlList) {
				listener.OnLoadComplete(imageUrlList);
			}
		}.execute();
	}
}
