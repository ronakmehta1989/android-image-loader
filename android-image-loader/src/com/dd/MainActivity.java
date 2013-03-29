package com.dd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.dd.loader.image.R;
import com.dd.util.log.L;

public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
	public static final String USE_DISK_CACHE = "USE_DISK_CACHE";
	public static final String USE_MEMORY_CACHE = "USE_MEMORY_CACHE";
	public static final String NUMBER_OF_THREADS = "NUMBER_OF_THREADS";

	private boolean useDiskCache = false;
	private boolean useMemoryCache = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();

	}

	@Override
	public void onClick(View view) {
		int id = view.getId();

		if (id == R.id.btnList) {
			startListActivity();
		} else if (id == R.id.btnGrid) {
			startGridActivity();
		}
	}

	private void startGridActivity() {
		Intent intent = new Intent(this, GridActivity.class);
		intent.putExtra(USE_DISK_CACHE, useDiskCache);
		intent.putExtra(USE_MEMORY_CACHE, useMemoryCache);
		startActivity(intent);
	}

	private void initView() {
		Button btnSimpleList = (Button) findViewById(R.id.btnList);
		btnSimpleList.setOnClickListener(this);

		Button btnGrid = (Button) findViewById(R.id.btnGrid);
		btnGrid.setOnClickListener(this);

		CheckBox cbUseDiskCache = (CheckBox) findViewById(R.id.cbUseDiskCache);
		cbUseDiskCache.setOnCheckedChangeListener(this);

		CheckBox cbUserMemoryCache = (CheckBox) findViewById(R.id.cbUserMemoryCache);
		cbUserMemoryCache.setOnCheckedChangeListener(this);
	}

	private int getNumberOfThreads() {
		EditText editText = (EditText) findViewById(R.id.editText);
		String inputText = editText.getText().toString().trim();
		int value = 1;

		try {
			value = Integer.parseInt(inputText);
		} catch (NumberFormatException e) {
			L.e(e.toString());
		}
		return value;
	}

	private void startListActivity() {
		Intent intent = new Intent(this, ListActivity.class);
		intent.putExtra(USE_DISK_CACHE, useDiskCache);
		intent.putExtra(USE_MEMORY_CACHE, useMemoryCache);
		intent.putExtra(NUMBER_OF_THREADS, getNumberOfThreads());
		startActivity(intent);
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
		int id = compoundButton.getId();

		if (id == R.id.cbUseDiskCache) {
			useDiskCache = isChecked;
		} else if (id == R.id.cbUserMemoryCache) {
			useMemoryCache = isChecked;
		}
	}
}
