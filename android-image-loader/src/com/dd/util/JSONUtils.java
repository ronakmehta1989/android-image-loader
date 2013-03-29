package com.dd.util;

import com.dd.util.log.L;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JSONUtils {

	public static String parseString(JSONObject obj, String value) {
		String result = null;

		try {

			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getString(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static int parseInt(JSONObject obj, String value) {
		int result = 0;
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getInt(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static long parseLong(JSONObject obj, String value) {
		long result = 0;
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getLong(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static double parseDouble(JSONObject obj, String value) {
		double result = 0;
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getDouble(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static boolean parseBoolean(JSONObject obj, String value) {
		boolean result = false;
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getBoolean(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static JSONArray parseArray(JSONObject obj, String value) {
		JSONArray result = new JSONArray();
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getJSONArray(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static JSONObject parseObject(JSONObject obj, String value) {
		JSONObject result = new JSONObject();
		try {
			if (obj.has(value) && !obj.isNull(value)) {
				result = obj.getJSONObject(value);
			}
		} catch (JSONException e) {
			L.e(e.toString());
		}
		return result;

	}

	public static boolean isObjectEmpty(JSONObject obj) {
		return obj == null || obj.length() == 0;

	}

	public static Object validateNull(Object value) {
		Object result = value;

		if (result == null) {
			result = JSONObject.NULL;
		}

		return result;

	}

}
